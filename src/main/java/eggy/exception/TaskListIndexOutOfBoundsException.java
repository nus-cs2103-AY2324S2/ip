package eggy.exception;

public class TaskListIndexOutOfBoundsException extends EggyException {
    public TaskListIndexOutOfBoundsException(int taskNumber, int tasksSize) {
        super(" Task number " + taskNumber + " is out of bounds for list of " + tasksSize + " task" + (tasksSize > 1 ? "s" : "") + ".");
    }
}
