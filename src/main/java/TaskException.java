public class TaskException extends InputException {
    public TaskException() {
        super(
                "Invalid task type\n"
                + "Please use either todo | event | deadline\n"
        );
    }
}
