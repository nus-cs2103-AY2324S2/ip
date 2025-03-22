package duke.exceptions;

/**
 * class for InvalidTaskIndexException for commands with indexing
 */
public class InvalidTaskIndexException extends DukeException {
    private int currentSize;

    /**
     * Constructor
     * @param size length of current taskList
     */
    public InvalidTaskIndexException(int size) {
        super();
        this.currentSize = size;
    }

    @Override
    public String toString() {
        return String.format(" Stop trolling and pick a positive number\n within %d\n", currentSize);
    }

}
