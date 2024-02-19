package Mitsuki;

/**
 * Contains information on the MissingDeadlineException class.
 *
 * @author Tee Chu Jie
 */
public class MissingDeadlineException extends MitsukiException {
    /**
     * The constructor for a MissingDeadlineException object.
     *
     * @param message Handled by the super constructor.
     */
    public MissingDeadlineException(String message) {
        super(message);
    }

    /**
     * Checks if a deadline task's deadline is given. Throws exception if it is not.
     *
     * @param tokens The array of strings containing the components of the task.
     * @throws MissingDeadlineException the exception that will be thrown if no deadline is given.
     */
    public static void validate(String[] tokens) throws MissingDeadlineException {
        if (tokens.length != 2) {
            throw new MissingDeadlineException("No Deadline Given");
        }
    }
}
