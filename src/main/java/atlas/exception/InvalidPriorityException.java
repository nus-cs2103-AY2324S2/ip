package atlas.exception;

/**
 * Represents an exception that occurs when the format of an event is invalid.
 * This class extends {@code AtlasException} to provide a more specific exception
 * for formatting issues related to event tasks in the Atlas application.
 */
public class InvalidPriorityException extends AtlasException {

    /**
     * Constructs a new {@code InvalidPriorityException} with the specified detail message.
     * The message typically contains information about wrong priority number entered
     *
     *
     * @param msg The detail message which provides more information invalid priority number entered.
     */
    public InvalidPriorityException(String msg) {
        super(msg);
    }
}
