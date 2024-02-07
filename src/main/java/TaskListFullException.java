public class TaskListFullException extends DukeException {
    //Used to indicate that the task list is full
    public TaskListFullException(String message) {
        super(message);
    }
}