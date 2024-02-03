package ellie.exception;

/**
 * Exception thrown when an unknown or unsupported input is encountered.
 */
public class UnknownInputException extends Exception {

    public UnknownInputException(String message) {
        super(message);
    }

}
