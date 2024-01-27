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
     *
     * @param message A string detailing the reason for the exception, 
     *                typically indicating which time information is missing.
     */
    public TimeMissingException(String message) {
        super(message); // Call to the superclass (Exception) constructor with the detailed message
    }
}
