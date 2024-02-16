package duke.exception;

/**
 * The class representing the exception thrown when the load file cannot be found.
 * */
public class LoadStorageException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Failed to load storage";

    public LoadStorageException() {
        super(ERROR_MESSAGE);
    }
}
