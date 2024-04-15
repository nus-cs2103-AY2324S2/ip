package exception;

/**
 * This class is an exception for when an operation is asked to be performed on an invalid task number.
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(String s) {
        super(String.format("Invalid task number %s. Please try again.", s));
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
