package exception;

/**
 * This class is an exception for when a duplicate task is attempted to be added.
 */
public class DuplicateTaskException extends Exception {

    public DuplicateTaskException() {
        super("A duplicate task exists in store!");
    }

    @Override
    public String toString() {
        return super.getMessage();
    }

}
