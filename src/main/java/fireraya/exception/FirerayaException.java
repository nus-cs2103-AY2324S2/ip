package fireraya.exception;

/**
 * This class handles the custom exceptions to be thrown in the program.
 */
public class FirerayaException extends Exception {

    /**
     * Constructor to throw a custom exception.
     *
     * @param message Message of the tasks to be printed.
     */
    public FirerayaException(String message) {
        super(message);
    }
}
