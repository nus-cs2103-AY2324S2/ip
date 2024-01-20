public class IncorrectTaskTypeException extends Exception {
    public IncorrectTaskTypeException() {
        super("Sorry, can I know the task type (eg. todo, deadline, event)?");
    }
}
