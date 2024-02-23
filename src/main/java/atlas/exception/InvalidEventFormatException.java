package atlas.exception;

/**
 * Represents an exception that occurs when the format of an event is invalid.
 * This class extends {@code AtlasException} to provide a more specific exception
 * for formatting issues related to event tasks in the Atlas application.
 */
public class InvalidEventFormatException extends AtlasException {

    /**
     * Constructs a new {@code InvalidEventFormatException} with the specified detail message.
     * The message typically contains information about the specific formatting issue encountered
     * in the event task.
     *
     * @param msg The detail message which provides more information about the event format issue.
     */
    public InvalidEventFormatException(String msg) {
        super(msg);
    }
}
