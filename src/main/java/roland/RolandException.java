package roland;

/**
 * The RolandException class is a custom exception that extends the base Exception class.
 * It is used to represent exceptions specific to the Roland task management application.
 * Instances of this exception are thrown to indicate errors or exceptional conditions
 * during the application's execution.
 *
 * @author wolffe88
 */

public class RolandException extends Exception {
    /**
     * Constructs a new RolandException with the specified error message.
     *
     * @param message The detail message that describes the exception.
     */
    public RolandException(String message) {
        super(message);
    }
}
