package atlas.exception;

/**
 * Represents an exception that occurs when the format of a deadline is invalid.
 * This class extends {@code AtlasException} to provide a more specific exception
 * related to the formatting issues of deadline tasks in the Atlas application.
 */
public class InvalidDeadlineFormatException extends AtlasException {
    /**
     * Constructs a new {@code InvalidDeadlineFormatException} with the specified detail message.
     * The message typically contains information about the specific formatting issue encountered.
     *
     * @param message The detail message which provides more information about the format issue.
     */
    public InvalidDeadlineFormatException(String message) {
        super(message);
    }
}
