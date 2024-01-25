public class TaskListIndexOutOfBoundsException extends EggyException {
    public TaskListIndexOutOfBoundsException(int taskNumber, int taskListSize) {
        super(" Task number " + taskNumber + " is out of bounds for list of " + taskListSize + " task" + (taskListSize > 1 ? "s" : "") + ".");
    }
}
