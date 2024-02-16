package grizzly.exceptions;

/**
 * This class implements an excpetion for when the bot fails to modify a task.
 *
 * @author delishad21
 */
public class TaskModificationException extends GrizzlyException {

    /**
     * Creates a TaskModificationException, thrown when user tries to modify a task but fails to.
     *
     * @param errorMsg Reason for task modification failure.
     */
    public TaskModificationException(String errorMsg) {
        super("Error Modifying Task! " + errorMsg);
    }
}
