package duke.exceptions;

/**
 * This class implements an excpetion for when the bot is unable to create a task.
 * 
 * @author delishad21
 */
public class TaskCreationException extends Exception {

    /**
     * Creates a TaskCreationException, thrown when a user task is unable to be created.
     * 
     * @param errorMsg Reason for task creation failure.
     */
    public TaskCreationException(String errorMsg) {
        super(errorMsg);
    }
    
}
