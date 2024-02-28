package Mitsuki;

/**
 * Contains information on the MissingEventTimingException class.
 *
 * @author Tee Chu Jie
 */
public class MissingEventTimingException extends MitsukiException {
    /**
     * The constructor for a MissingEventTimingException object.
     *
     * @param message Handled by the super constructor.
     */
    public MissingEventTimingException(String message) {
        super(message);
    }

    /**
     * Checks if an event task's start and end timings are given. Throws exception either one is not.
     *
     * @param tokens The array of strings containing the components of the task.
     * @throws MissingEventTimingException the exception that will be thrown if
     *          either event start or end timing is not given.
     */
    public static void validate(String[] tokens) throws MissingEventTimingException {
        assert tokens != null : "tokens should not be null";
        if (tokens.length != 3) {
            throw new MissingEventTimingException("No Event Timing Given");
        }
    }
}
