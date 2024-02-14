package chatbro;

/**
 * Exception class for invalid user input for date/time format parsing.
 */
public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
