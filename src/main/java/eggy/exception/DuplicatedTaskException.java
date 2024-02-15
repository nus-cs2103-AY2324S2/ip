package eggy.exception;

/**
 * Represents an exception when the user tries to add a task that already exists.
 */
public class DuplicatedTaskException extends EggyException {
    /**
     * Constructs the DuplicatedTaskException.
     */
    public DuplicatedTaskException() {
        super(" Task already exists.");
    }
}
