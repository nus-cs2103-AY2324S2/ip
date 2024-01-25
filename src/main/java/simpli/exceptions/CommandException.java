package simpli.exceptions;

public class CommandException extends SimpliException {
    public CommandException() {
        super();
    }

    public CommandException(String errorMsg) {
        super(errorMsg);
    }
}
