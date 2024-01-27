package exceptions;

public class ConvoBotException extends Exception {
    public ConvoBotException() {
        super();
    }

    public ConvoBotException(String message) {
        super(message);
    }

    public ConvoBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
