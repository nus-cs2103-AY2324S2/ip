package simpli.exceptions;

public class TaskException extends ActionException {
    public TaskException() {
        super();
    }

    public TaskException(String errorMsg) {
        super(errorMsg);
    }
}
