package duke.exceptions;

/**
 * This class implements the Exception for when an error occurs on deletion of a record.
 */
public class RecordDeletionException extends DukeException {
    /**
     * Creates a RecordDeletionException.
     *
     * @param errorMsg Reason for record deletion failure.
     */
    public RecordDeletionException(String errorMsg) {
        super("Error Deleting Record! " + errorMsg);
    }

}
