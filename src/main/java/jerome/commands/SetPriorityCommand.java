package jerome.commands;

import java.util.regex.Pattern;

import jerome.tasklist.Priority;
import jerome.tasklist.Task;

/**
 * Represents the command to delete a task to the data storage.
 *
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class SetPriorityCommand extends Command {

    /**
     * Represents the command word for the Delete command.
     */
    public static final String COMMAND_WORD = "triage";

    /**
     * Represents a regular expression pattern for matching a specific format.
     */
    public static final Pattern SET_PRIORITY_ARGUMENTS_FORMAT =
            Pattern.compile("(?<targetIndex>\\d+) (?<priorityLevel>\\S+)");


    /**
     * Represents the usage message for the Delete command.
     * Usage Example: delete 3.
     */
    public static final String MESSAGE_USAGE = "\t "
            + COMMAND_WORD + ": Change priority level of a Task (LOW/MEDIUM/HIGH).\n"
            + "\t Example: " + COMMAND_WORD
            + " 1 MEDIUM";

    /**
     * Represents the message after the delete command is successfully executed.
     * Contains the deleted task information, and task count in the database.
     */
    public static final String MESSAGE_SUCCESS = "\t Noted, the priority of this task has been modified:\n"
            + "\t  %s";

    /**
     * Represents an invalid message when user input is dirty.
     */
    public static final String MESSAGE_INVALID_ID = "\t "
            + COMMAND_WORD + ": Change priority level of a Task (LOW/MEDIUM/HIGH).\n"
            + "\t Error: Please set a valid integer of 1 or larger.\n"
            + "\t Example: " + COMMAND_WORD
            + " 1 MEDIUM";

    /**
     * Represents an error message indicating that an invalid priority value was provided.
     */
    public static final String MESSAGE_INVALID_PRIORITY = "\t "
            + COMMAND_WORD + ": Change priority level of a Task (LOW/MEDIUM/HIGH).\n"
            + "\t Error: Please use a valid priority level: LOW, MEDIUM, HIGH.\n"
            + "\t Example: " + COMMAND_WORD
            + " 1 MEDIUM";

    /**
     * Represents a message indicating that a task has not been created yet.
     */
    public static final String MESSAGE_TASK_NOT_CREATED_YET = " \t This is an invalid index\n"
            + "\t There are %d tasks available.\n";

    /**
     * Represents the index of the task to be deleted.
     */
    private int targetIndex;
    private Priority priority;

    /**
     * Represents command to delete a task from the task list.
     *
     * @param targetIndex the index of the task to be deleted.
     */
    public SetPriorityCommand(int targetIndex, Priority priority) {
        this.targetIndex = targetIndex;
        this.priority = priority;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @return the CommandResult indicating the result of the execution which could pass or fail.
     */
    @Override
    public CommandResult execute() {
        try {
            Task task = dataStorage.getTask(targetIndex);
            task.setPriority(this.priority);
            dataStorage.rebuildStorage();
            return new CommandResult(String.format(MESSAGE_SUCCESS, task, dataStorage.getTaskCount()));
        } catch (IndexOutOfBoundsException iobe) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_CREATED_YET, dataStorage.getTaskCount()));
        }
    }
}
