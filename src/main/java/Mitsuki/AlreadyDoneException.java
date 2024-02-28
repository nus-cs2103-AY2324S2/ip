package Mitsuki;
/**
 * Contains information on the AlreadyDoneException class.
 *
 * @author Tee Chu Jie
 */
public class AlreadyDoneException extends MitsukiException {
    /**
     * The constructor for an AlreadyDoneException object.
     *
     * @param message Handled by the super constructor.
     */
    public AlreadyDoneException(String message) {
            super(message);
    }

    /**
     * Checks if the task was done. If task was done, throws exception.
     *
     * @param task the task to be checked
     * @throws AlreadyDoneException the exception that will be thrown if task was already done.
     */
    public static void validate(Task task) throws AlreadyDoneException {
        assert task != null : "task should not be null";
        if (task.isDone) {
            throw new AlreadyDoneException("Task Already Marked As Done");
        }
    }
}

