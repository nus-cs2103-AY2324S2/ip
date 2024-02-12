package aurora.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import aurora.objects.AuroraException;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * The EventCommand class handles the "event" command.
 */
public class EventCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String command;

    /**
     * Constructor for the EventCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     * @param command Full command input.
     */
    public EventCommand(TaskList taskList, Ui ui, Storage storage, String command) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public String handle() throws AuroraException {
        String message = "Command not executed.";
        String[] descriptionAndDateSplit = Parser.splitAtFirstBlank(this.command);
        if (descriptionAndDateSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        }
        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] descriptionSplit = Parser.splitAtFirstFrom(descriptionAndDate);
        if (descriptionSplit.length != 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        }
        String startEnd = descriptionSplit[1];
        String[] startEndSplit = Parser.splitAtFirstTo(startEnd);
        if (startEndSplit.length != 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        } else {
            try {
                this.taskList.addEvent(descriptionSplit[0], Parser.parseDate(startEndSplit[0].trim()),
                        Parser.parseDate(startEndSplit[1].trim()));
                message = this.ui.getEchoAddTaskString(this.taskList);
            } catch (DateTimeParseException e) {
                throw new AuroraException(AuroraException.INVALID_DATE_FORMAT);
            }
        }
        try {
            storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            message = "Unable to save event to file: " + exception.getMessage();
        }
        return message;
    }

}
