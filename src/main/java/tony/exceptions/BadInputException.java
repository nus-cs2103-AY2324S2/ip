package tony.exceptions;
/**
 * This Exception class represents exceptions thrown for bad user input.
 */
public class BadInputException extends TonyException {
    /**
     * This is a constructor for the exception.
     *
     * @param message The message to be thrown.
     */
    public BadInputException(String message) {
        super(message);
    }

}
