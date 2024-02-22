package duke;

/**
 * Represents an exception thrown when an invalid index is used to access a list of tasks in the Duke application.
 */
public class IndexOutOfRangeException extends DukeException {

    /**
     * Constructs a new IndexOutOfRangeException with a detailed error message.
     *
     * @param idx  The invalid index that was used.
     * @param size The total number of tasks in the list.
     */
    public IndexOutOfRangeException(int idx, int size) {
        super(idx + " is not valid! It must be between 1 and the number of tasks in the list. There are " + size + " tasks.");
    }
}
