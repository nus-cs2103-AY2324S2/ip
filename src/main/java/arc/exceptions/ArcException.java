package arc.exceptions;

/**
 * Represents the base class for all custom exceptions in the Arc application.
 * This abstract class extends the standard Java {@link Exception} class,
 * providing a framework for handling application-specific exceptions with
 * customized error messages.
 */
public abstract class ArcException extends Exception {
    /**
     * Constructs a new ArcException with the specified detail message.
     * The detail message is modified to include a standard prefix "OOPS!!!" to
     * signify an error specific to the Arc application.
     *
     * @param errorMessage The detail message (which is saved for later retrieval
     *                     by the {@link Throwable#getMessage()} method).
     */
    public ArcException(String errorMessage) {
        super(String.format("OOPS!!! %s", errorMessage));
    }
}
