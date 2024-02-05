public class TaskListFullException extends Exception {
    //Used to indicate that the task list is full
    public TaskListFullException(String message) {
        super(message);
    }
}