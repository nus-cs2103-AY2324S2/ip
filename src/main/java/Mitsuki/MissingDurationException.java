package Mitsuki;

public class MissingDurationException extends MitsukiException {
    /**
     * The constructor for a MissingDurationException object.
     *
     * @param message Handled by the super constructor.
     */
    public MissingDurationException(String message) {
        super(message);
    }

    /**
     * Checks if an event task's start and end timings are given. Throws exception either one is not.
     *
     * @param tokens The array of strings containing the components of the task.
     * @throws MissingDurationException the exception that will be thrown if
     *          either event start or end timing is not given.
     */
    public static void validate(String[] tokens) throws MissingDurationException {
        assert tokens != null : "tokens should not be null";
        if (tokens.length != 2) {
            throw new MissingDurationException("No Duration Given");
        }
    }
}
