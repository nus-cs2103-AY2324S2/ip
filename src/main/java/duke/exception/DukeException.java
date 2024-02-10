package duke.exception;

/**
 * Represents exceptions specific to the Duke application. This class extends the standard Java {@code Exception}
 * class and provides additional functionality to handle exceptions that occur during the execution of the Duke
 * application. It is designed to encapsulate errors related to the application's logic and operations, providing
 * a meaningful error message to the user.
 */
public class DukeException extends Exception{
    protected String message;
    
    /**
     * Constructs a new {@code DukeException} with the specified detail message. The message can be retrieved later
     * by the {@link Throwable#getMessage()} method.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public DukeException(String message) {
        super(message);
    }
}
