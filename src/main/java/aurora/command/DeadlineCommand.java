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
 * The DeadlineCommand class handles the "deadline" command.
 */
public class DeadlineCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String command;

    /**
     * Constructor for the DeadlineCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     * @param command Full command input.
     */
    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String command) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public String handle() throws AuroraException {
        String[] descriptionAndDateSplit = Parser.splitAtFirstBlank(this.command);
        validateCommandFormat(descriptionAndDateSplit);

        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] splitVariables = Parser.splitAtFirstBy(descriptionAndDate);
        validateDeadlineFormat(splitVariables);

        String description = splitVariables[0];
        String dateString = splitVariables[1];
        LocalDateTime dateLdt = parseDate(dateString);

        this.taskList.addDeadline(description, dateLdt);
        String message = this.ui.getEchoAddTaskString(this.taskList);

        saveTasks();
        return message;
    }

    /**
     * Helper method for the validation of the input format.
     *
     * @param descriptionAndDateSplit String array containing the full command.
     * @throws AuroraException If the input format is invalid.
     */
    private void validateCommandFormat(String[] descriptionAndDateSplit) throws AuroraException {
        if (descriptionAndDateSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DEADLINE_FORMAT);
        }
    }

    /**
     * Helper method for the validation of the input format.
     *
     * @param splitVariables String array containing the description and the date of the deadline.
     * @throws AuroraException If the input format is invalid.
     */
    private void validateDeadlineFormat(String[] splitVariables) throws AuroraException {
        if (splitVariables.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DEADLINE_FORMAT);
        }
    }

    /**
     * Helper method to parse the date
     *
     * @param dateString String representation of the date of the deadline.
     * @return LocalDateTime object corresponding to the String representation.
     * @throws AuroraException If the date is of incorrect format.
     */
    private LocalDateTime parseDate(String dateString) throws AuroraException {
        try {
            return Parser.parseDate(dateString.trim());
        } catch (DateTimeParseException e) {
            throw new AuroraException(AuroraException.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Helper method to save the task.
     *
     * @throws AuroraException if the task was not successfully saved.
     */
    private void saveTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save deadline to file: " + exception.getMessage());
        }
    }


}
