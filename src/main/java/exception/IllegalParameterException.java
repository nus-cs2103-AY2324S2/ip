package exception;

public class IllegalParameterException extends BluException {
    public IllegalParameterException(String message) {
        super("Illegal Parameter: " + message);
    }
}
