package homie;

/**
 * Deadline exception class. Handles all exceptions related to Deadline class.
 * Thrown when no description for deadline task is given, or format of due date is wrong.
 */
public class DeadlineException extends Exception {
    /**
     * Constructor for DeadlineException class.
     *
     * @param message The error message.
     */
    public DeadlineException(String message) {
        super("Bruh... " + message + ". \nPlease follow the format: \ndeadline"
                + " {DEADLINE_DESCRIPTION} /by {dd MM yyyy HHmm}");
    }
}
