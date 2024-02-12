package bob.exception;

public class ParameterNotFoundException extends BobException {
    private static final String MESSAGE = "tell me what's your %s";

    public ParameterNotFoundException(String parameter) {
        super(String.format(MESSAGE, parameter));
    }

    public ParameterNotFoundException(String[] parameters) {
        super(String.format(MESSAGE, String.join(" or ", parameters)));
    }
}
