public class WasNotDoneException extends DukeException {
    public WasNotDoneException(String message) {
        super(message);
    }

    public static void validate(Task task) throws WasNotDoneException {
        if (!task.isDone) {
            throw new WasNotDoneException("Task Was Not Done");
        }
    }
}
