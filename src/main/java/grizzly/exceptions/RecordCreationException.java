package grizzly.exceptions;

/**
 * This class implements an excpetion for when the bot is unable to create a task.
 *
 * @author delishad21
 */
public class RecordCreationException extends GrizzlyException {

    /**
     * Creates a RecordCreationException, thrown when a task is unable to be created.
     *
     * @param errorMsg Reason for record creation failure.
     */
    public RecordCreationException(String errorMsg) {
        super("Error Creating Record! " + errorMsg);
    }

}
