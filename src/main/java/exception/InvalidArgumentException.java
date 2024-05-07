package exception;

/**
 * Represents an exception when an invalid argument is given.
 */
public class InvalidArgumentException extends Exception {
    /**
     * Returns the error message when an invalid argument is given.
     */
    @Override
    public String toString() {
        return "OOPS!!! Please enter a valid task number.";
    }
}
