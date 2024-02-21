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
        validateFormatFirst(descriptionAndDateSplit);

        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] descriptionSplit = Parser.splitAtFirstFrom(descriptionAndDate);
        validateFormatSecond(descriptionSplit);

        String startEnd = descriptionSplit[1];
        String[] startEndSplit = Parser.splitAtFirstTo(startEnd);
        validateFormatSecond(startEndSplit);

        LocalDateTime startDate = parseDate(startEndSplit[0].trim());
        LocalDateTime endDate = parseDate(startEndSplit[1].trim());

        this.taskList.addEvent(descriptionSplit[0], startDate, endDate);
        message = this.ui.getEchoAddTaskString(this.taskList);

        saveTasks();
        return message;
    }

    /**
     * First helper function to validate input format.
     * @param parts String array containing the full command.
     * @throws AuroraException If an invalid input is detected.
     */
    private void validateFormatFirst(String[] parts) throws AuroraException {
        if (parts.length < 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        }
    }

    /**
     * First helper function to validate input format.
     * @param parts String array containing the full command.
     * @throws AuroraException If an invalid input is detected.
     */
    private void validateFormatSecond(String[] parts) throws AuroraException {
        if (parts.length != 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        }
    }

    /**
     * Helper function to parse the dates.
     * @param dateString String representation of the dates.
     * @return LocalDateTime object
     * @throws AuroraException If the format of the date is incorrect.
     */
    private LocalDateTime parseDate(String dateString) throws AuroraException {
        try {
            return Parser.parseDate(dateString);
        } catch (DateTimeParseException e) {
            throw new AuroraException(AuroraException.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Helper method to save tasks.
     * @throws AuroraException If the taskList was not saved successfully.
     */
    private void saveTasks() throws AuroraException {
        try {
            storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save event to file: " + exception.getMessage());
        }
    }


}
