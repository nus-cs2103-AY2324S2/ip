package duke.exceptions;

/**
 * This class implements an excpetion for when the bot fails to modify a task.
 * 
 * @author delishad21
 */
public class TaskModificationException extends Exception {
    
    /**
     * Basic constructor.
     * 
     * @param errorMsg Reason for task modification failure.
     */
    public TaskModificationException(String errorMsg) {
        super(errorMsg);
    }
}
