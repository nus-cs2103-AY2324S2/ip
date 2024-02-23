package atlas.exception;

/**
 * Represents exceptions specific to the Atlas application.
 * This class extends the {@code Exception} class and is used to
 * handle various exceptional scenarios specific to the application's workflow.
 */
public class AtlasException extends Exception {
    /**
     * Constructs a new {@code AtlasException} with the specified detail message.
     * The message can be retrieved later by the {@code Throwable.getMessage()} method.
     *
     * @param message The detail message which provides more information about the exception.
     */
    public AtlasException(String message) {
        super(message);
    }
}
