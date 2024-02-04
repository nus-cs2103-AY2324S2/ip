package jerome.commands;

import jerome.exception.MalformedUserInputException;
import jerome.tasklist.Task;

/**
 * Represents the command to delete a task to the data storage.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class DeleteCommand extends Command {

    /**
     * Represents the command word for the Delete command.
     */
    public static final String COMMAND_WORD = "delete";

    /**
     * Represents the usage message for the Delete command.
     * Usage Example: delete 3.
     */
    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Delete a selected event when it exists. \n"
            + "\t Example: " + COMMAND_WORD
            + " 1 ";

    /**
     * Represents the message after the delete command is successfully executed.
     * Contains the deleted task information, and task count in the database.
     */
    public static final String MESSAGE_SUCCESS = "\t Noted, I have removed this task:\n"
            + "\t  %s\n"
            + "\t Now you have %d task(s) in the list.";

    /**
     * Represents an invalid message when user input is dirty.
     */
    public static final String MESSAGE_INVALID_ID = "\t Please enter a integer that is 1 or larger.";

    /**
     * Represents a message indicating that a task has not been created yet.
     */
    public static final String MESSAGE_TASK_NOT_CREATED_YET = " \t This is an invalid index\n"
            + "\t There are %d tasks available.\n";

    /**
     * Represents the index of the task to be deleted.
     */
    private int targetIndex;

    /**
     * Represents command to delete a task from the task list.
     * @param targetIndex the index of the task to be deleted
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command to delete a task from the task list.
     * @return the CommandResult indicating the result of the execution which could pass or fail.
     */
    @Override
    public CommandResult execute() {
        try {
            Task task = dataStorage.getTask(targetIndex);
            dataStorage.deleteTask(targetIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, task, dataStorage.getTaskCount()));
        } catch (MalformedUserInputException e) {
            return new CommandResult(e.getMessage());
        } catch (IndexOutOfBoundsException iobe) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_CREATED_YET, dataStorage.getTaskCount()));
        }
    }
}
