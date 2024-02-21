package exceptions;

/**
 * Custom exception that handles edge cases such as invalid command etc.
 * @author Koo Zhuo Hui
 */
public class HowieException extends Exception {

    /**
     * Constructs the exception
     * @param message the message to be printed to user.
     */
    public HowieException(String message) {
        super(message);
    }
}
