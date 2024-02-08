package gronk;

/**
 * MissingTasksException class.
 * This exception is thrown when the filename for a
 * Storage object is not found.
 */
public class MissingTasksException extends Exception {
    public MissingTasksException() {}

    public MissingTasksException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "\tFile not found!";
    }
}
