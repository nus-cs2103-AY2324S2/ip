package yoda.exceptions;
/**
 * Exception thrown when a required time information is missing.
 * This can occur in scenarios such as creating a deadline or event task
 * without specifying the necessary time details.
 */
public class TimeMissingException extends Exception {

    /**
     * Constructs a TimeMissingException with a detailed message.
     * This message provides information about the missing time detail, 
     * which is essential for certain types of tasks (e.g., deadlines, events).
     */
    public TimeMissingException() {
        super("Required, the task time is, yes.");
    }

    /**
     * Constructs a TimeMissingException with a customised message.
     * @param message provides information about the missing time detail
     */
    public TimeMissingException(String message) {
        super(message);
    }
}
