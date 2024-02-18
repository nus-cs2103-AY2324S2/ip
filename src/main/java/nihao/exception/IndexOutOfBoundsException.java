package nihao.exception;

/**
 * Represents an Exception thrown when trying to access a non-existent element of an ArrayList.
 */
public class IndexOutOfBoundsException extends Exception {
    /**
     * Class constructor specifying the provided index and the maximum size of the ArrayList.
     */
    public IndexOutOfBoundsException(int index, int size) {
        super("IndexOutOfBoundsException: " + "index " + index + " out of bounds for size " + size + ".");
    }
}
