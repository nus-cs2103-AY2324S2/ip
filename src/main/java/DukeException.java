public class DukeException extends Exception {
    public static final String INVALID_TASK_INDEX = "Invalid task index!";
    public DukeException(String message) {
        super(message);
    }
}
