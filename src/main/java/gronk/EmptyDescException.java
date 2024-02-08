package gronk;

/**
 * EmptyDescException class.
 * Thrown when a command such as "todo" or "deadline"
 * is run without a subsequent task description.
 */
public class EmptyDescException extends Exception {
    public EmptyDescException() {}

    public EmptyDescException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "\tUh oh! Looks like you forgot a description of the task!";
    }
}
