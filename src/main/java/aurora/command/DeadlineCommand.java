package aurora.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import aurora.objects.AuroraException;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/** The DeadlineCommand class represents the "deadline" command. */
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
     * Constructs a DeadlineCommand object.
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
     * Validates if a deadline command is legitimate by checking if a description for the deadline was
     * given by the user.
     *
     * @param descriptionAndDateSplit String array containing "deadline" in the 0th index, and the description
     *                                along with the date in the 1st index if it exists.
     * @throws AuroraException If the 1st index does not exist.
     */
    private void validateCommandFormat(String[] descriptionAndDateSplit) throws AuroraException {
        if (descriptionAndDateSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DEADLINE_FORMAT);
        }
    }

    /**
     * Validates if a deadline command is legitimate by checking if a description and a datetime
     * for the deadline was given by the user.
     *
     * @param splitVariables String array containing the description of the deadline in the 0th index, and the
     *                       datetime in the 1st index if it exists.
     * @throws AuroraException If the 1st index does not exist.
     */
    private void validateDeadlineFormat(String[] splitVariables) throws AuroraException {
        if (splitVariables.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DEADLINE_FORMAT);
        }
    }

    /**
     * Returns a LocalDateTime object after parsing the String representation of the datetime of the deadline.
     *
     * @param dateString String representation of the datetime of the deadline.
     * @return LocalDateTime object representing the datetime of the deadline.
     * @throws AuroraException If the datetime String is of incorrect format.
     */
    private LocalDateTime parseDate(String dateString) throws AuroraException {
        try {
            return Parser.parseDate(dateString.trim());
        } catch (DateTimeParseException e) {
            throw new AuroraException(AuroraException.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Saves the task list containing the new deadline object created to the storage file.
     *
     * @throws AuroraException If an error occurs while saving the task list to the storage file.
     */
    private void saveTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save deadline to file: " + exception.getMessage());
        }
    }


}
