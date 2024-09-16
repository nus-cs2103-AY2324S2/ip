package duke.exception;

/**
 * The DukeException class represents a custom exception specific to the Duke application.
 * <p>
 * This exception is used to handle errors and exceptional situations within the Duke application. It extends
 * the standard Java Exception class and is designed to provide a mechanism for capturing and handling
 * application-specific issues that may arise during the execution of Duke's functionalities.
 * </p>
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified error message.
     * <p>
     * This constructor is used to create instances of DukeException with a custom error message that describes
     * the nature of the exception. It allows for informative and user-friendly messages to be associated with
     * instances of this exception, aiding in the identification and resolution of issues within the Duke application.
     * </p>
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
