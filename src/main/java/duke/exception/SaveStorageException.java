package duke.exception;

/**
 * The class representing the exception thrown when the save file cannot be found.
 * */
public class SaveStorageException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Failed to save to storage.";

    public SaveStorageException() {
        super(ERROR_MESSAGE);
    }
}
