package duke.exception;

/**
 * The class representing the exception thrown when the save file cannot be found.
 * */
public class SaveStorageException extends DukeException {
    public SaveStorageException() {
        super("Failed to save to storage.");
    }
}
