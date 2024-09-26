package seedu.banter.errors;


/**
 * Represents an error that occurs when the user is unable to save the task list.
 */
public class UnableToSaveTaskListError extends BanterError {
    /**
     * Constructs a new UnableToSaveTaskListError object.
     */
    public UnableToSaveTaskListError() {
        super(Errors.UNABLE_TO_SAVE_TASK_LIST);
    }
}
