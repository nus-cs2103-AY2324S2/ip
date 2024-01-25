package simpli.exceptions;

public class TaskException extends CommandException {
    public TaskException() {
        super();
    }

    public TaskException(String errorMsg) {
        super(errorMsg);
    }
}
