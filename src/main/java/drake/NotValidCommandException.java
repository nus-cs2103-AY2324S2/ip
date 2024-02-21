package drake;

/**
 * This exception is thrown when an attempt is made to execute an invalid command
 * in the Drake application. It serves as a specific signal that a user has attempted
 * to input a command that the application does not recognize or cannot process.
 */
public class NotValidCommandException extends Exception {
    public NotValidCommandException(String message) {
        super(message);
    }
}
