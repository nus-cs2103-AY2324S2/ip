/**
 * Contains information on the EmptyTaskException class.
 *
 * @author Tee Chu Jie
 */
public class EmptyTaskException extends MitsukiException {
    /**
     * The constructor for an EmptyTaskException object.
     *
     * @param message Handled by the super constructor.
     */
    public EmptyTaskException(String message) {
        super(message);
    }

    /**
     * Checks if a todo task's description is empty. Throws exception if it is.
     *
     * @param description the task's description.
     * @throws EmptyTaskException the exception that will be thrown if no task description is given.
     */
    public static void validate(String description) throws EmptyTaskException {
        if (description.isEmpty()) {
            throw new EmptyTaskException("Empty Task Given");
        }
    }

    /**
     * Checks if a deadline or event task's description is empty. Throws exception if it is.
     *
     * @param tokens The array of strings containing the components of the task.
     * @throws EmptyTaskException the exception that will be thrown if no task description is given.
     */
    public static void timedValidate(String[] tokens) throws EmptyTaskException {
        if (tokens[0].isBlank()) {
            throw new EmptyTaskException("Empty Task Given");
        }
    }
}
