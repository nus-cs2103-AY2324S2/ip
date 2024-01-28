package duke.exceptions;

/**
 * This class implements an excpetion for when the bot is unable to create a task.
 * 
 * @author delishad21
 */
public class TaskCreationException extends Exception {

    /**
     * Basic constructor.
     * 
     * @param errorMsg Reason for task creation failure.
     */
    public TaskCreationException(String errorMsg) {
        super(errorMsg);
    }
    
}
