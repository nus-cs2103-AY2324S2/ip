package seedu.banter.errors;


/**
 * Represents an error that occurs when the user is unable to load the task list.
 */
public class UnableToLoadTaskListError extends BanterError {
    /**
     * Constructs a new UnableToLoadTaskListError object.
     */
    public UnableToLoadTaskListError() {
        super(Errors.UNABLE_TO_LOAD_TASK_LIST);
    }
}
