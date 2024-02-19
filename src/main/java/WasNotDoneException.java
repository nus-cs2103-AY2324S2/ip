/**
 * Contains information on the WasNotDoneException class.
 *
 * @author Tee Chu Jie
 */
public class WasNotDoneException extends MitsukiException {
    /**
     * The constructor for a WasNotDoneException object.
     *
     * @param message Handled by the super constructor.
     */
    public WasNotDoneException(String message) {
        super(message);
    }

    /**
     * Checks if the task was done. If task was not done yet, throws exception.
     *
     * @param task the task to be checked
     * @throws WasNotDoneException the exception that will be thrown if task was not done yet.
     */
    public static void validate(Task task) throws WasNotDoneException {
        if (!task.isDone) {
            throw new WasNotDoneException("Task Was Not Done");
        }
    }
}
