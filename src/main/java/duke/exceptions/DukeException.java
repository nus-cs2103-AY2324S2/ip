package duke.exceptions;

/**
 * Represents the base class for all custom exceptions in the Duke application.
 * This abstract class extends the standard Java {@link Exception} class,
 * providing a framework for handling application-specific exceptions with
 * customized error messages.
 */
public abstract class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     * The detail message is modified to include a standard prefix "OOPS!!!" to
     * signify an error specific to the Duke application.
     *
     * @param errorMessage The detail message (which is saved for later retrieval
     *                     by the {@link Throwable#getMessage()} method).
     */
    public DukeException(String errorMessage) {
        super(String.format("OOPS!!! %s", errorMessage));
    }
}
