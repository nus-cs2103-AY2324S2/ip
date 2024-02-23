package aurora.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import aurora.objects.AuroraException;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/** The EventCommand class represents the "event" command. */
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
     * Constructs an EventCommand object.
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
        validateFormatForDescription(descriptionAndDateSplit);

        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] descriptionSplit = Parser.splitAtFirstFrom(descriptionAndDate);
        validateFormatForComponents(descriptionSplit);

        String startEnd = descriptionSplit[1];
        String[] startEndSplit = Parser.splitAtFirstTo(startEnd);
        validateFormatForComponents(startEndSplit);

        LocalDateTime startDate = parseDate(startEndSplit[0].trim());
        LocalDateTime endDate = parseDate(startEndSplit[1].trim());

        this.taskList.addEvent(descriptionSplit[0], startDate, endDate);
        message = this.ui.getEchoAddTaskString(this.taskList);

        saveTasks();
        return message;
    }

    /**
     * Validates the event command input by checking if it contains at least a description.
     *
     * @param parts String array containing "event" at index 0 and the other inputs at index 1.
     * @throws AuroraException If index 1 does not exist.
     */
    private void validateFormatForDescription(String[] parts) throws AuroraException {
        if (parts.length < 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        }
    }

    /**
     * Validates the event command input by checking if it contains the correct number of components.
     *
     * @param parts String array containing parts of the event command input to validate.
     * @throws AuroraException If an incorrect number of components is detected in the input.
     */
    private void validateFormatForComponents(String[] parts) throws AuroraException {
        if (parts.length != 2) {
            throw new AuroraException(AuroraException.INVALID_EVENT_FORMAT);
        }
    }

    /**
     * Returns a LocalDateTime object after parsing the String representation of
     * one of the 2 datetime associated with the event.
     *
     * @param dateString String representation of the datetime to be parsed.
     * @return LocalDateTime object representing one of the 2 datetime associated with the event.
     * @throws AuroraException If the datetime String is of incorrect format.
     */
    private LocalDateTime parseDate(String dateString) throws AuroraException {
        try {
            return Parser.parseDate(dateString);
        } catch (DateTimeParseException e) {
            throw new AuroraException(AuroraException.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Saves the task list containing the new event object created to the storage file.
     *
     * @throws AuroraException If an error occurs while saving the task list to the storage file.
     */
    private void saveTasks() throws AuroraException {
        try {
            storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save event to file: " + exception.getMessage());
        }
    }


}
