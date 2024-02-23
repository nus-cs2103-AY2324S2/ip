package eggy.exception;

/**
 * Represents an exception when the task is out of bounds.
 */
public class TaskListIndexOutOfBoundsException extends EggyException {
    /**
     * Constructs a TaskListIndexOutOfBoundsException.
     *
     * @param taskNumber The task number that is out of bounds.
     * @param tasksSize The size of the list of tasks.
     */
    public TaskListIndexOutOfBoundsException(int taskNumber, int tasksSize) {
        super(" Task number " + taskNumber + " is out of bounds for list of " + tasksSize + " task"
                + (tasksSize > 1 ? "s" : "") + ".");
    }
}
