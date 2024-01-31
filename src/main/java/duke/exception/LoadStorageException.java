package duke.exception;

/**
 * The class representing the exception thrown when the load file cannot be found.
 * */
public class LoadStorageException extends DukeException {
    public LoadStorageException() {
        super("Failed to load storage.");
    }
}
