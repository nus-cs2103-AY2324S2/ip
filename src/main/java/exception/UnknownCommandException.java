package exception;

/**
 * Represents an exception when an unknown command is given.
 */
public class UnknownCommandException extends Exception {
    /**
     * Returns the error message when an unknown command is given.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
