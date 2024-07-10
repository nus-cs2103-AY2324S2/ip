package homie;

/**
 * DeadlineException thrown when no description for deadline task is given, or format of due date is wrong when
 * creating a deadline task.
 */
public class DeadlineException extends Exception {
    /**
     * Constructor for DeadlineException class.
     *
     * @param message The error message.
     */
    public DeadlineException(String message) {
        super("Bruh... " + message + "\nPlease follow the format:\ndeadline "
                + "{DEADLINE_DESCRIPTION} /by {dd MM yyyy HHmm}");
    }
}
