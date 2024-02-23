package jerome.commands;

import jerome.exception.MalformedUserInputException;

/**
 * Represents the command to mark the task as uncompleted.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class UnmarkCommand extends Command {

    /**
     * Represents the command word for unmark Event command.
     */
    public static final String COMMAND_WORD = "unmark";

    /**
     * Represents how this command should be used.
     */
    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Marks an event as uncompleted.\n"
            + "\t Example: " + COMMAND_WORD
            + " 1 ";

    /**
     * Represents how data should be presented to user upon success.
     */
    public static final String MESSAGE_SUCCESS = "\t Nice! I've marked this task as uncompleted:\n"
            + "\t %s";


    /**
     * Represents what should be displayed when an error is met.
     */
    public static final String MESSAGE_INVALID_ID = "\t Enter the command as such: unmark 1\n"
            + "\t Error: Please enter a integer that is 1 or larger.";

    /**
     * Represents an error message representing a valid, but not yet created index.
     */
    public static final String MESSAGE_TASK_NOT_CREATED_YET = "\t Enter the command as such: unmark 1"
            + "\t Error: This task is not created yet.\n"
            + "\t There are %d tasks available.";


    private int targetIndex;

    /**
     * Represents a command to unmark an event as completed.
     * @param targetIndex the index to be unmarked as completed.
     */
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command to execute the unmarking of tasks as completed.
     *
     * @return a CommandResult if the operation has succeeded or failed.
     */
    @Override
    public CommandResult execute() {
        try {
            dataStorage.setTaskStatus(targetIndex, false);
            return new CommandResult(String.format(MESSAGE_SUCCESS, dataStorage.getTask(targetIndex)));
        } catch (MalformedUserInputException e) {
            return new CommandResult(MarkCommand.MESSAGE_INVALID_ID);
        } catch (IndexOutOfBoundsException iobe) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_CREATED_YET, dataStorage.getTaskCount()));
        }
    }
}
