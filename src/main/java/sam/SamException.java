package sam;

/**
 * The exception class for errors specific to Sam .
 *
 * Extends the Exception class to represent custom exceptions that can occur within Sam.
 * Each instance of SamException contains a message detailing the specific error.
 */
public class SamException extends Exception {
    /**
     * Constructs a new SamException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public SamException(String message) {
        super(message);
    }
}
