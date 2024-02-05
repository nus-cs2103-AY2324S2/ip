package simpli.exceptions;

public class TaskException extends SimpliException {
    public TaskException() {
        super();
    }

    public TaskException(String errorMsg) {
        super(errorMsg);
    }
}
