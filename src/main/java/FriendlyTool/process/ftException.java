package FriendlyTool.process;

/**
 * Handles Errors and Exceptions in the program.
 */
public class ftException extends  Exception {
    /**
     * Constructs the ftException.
     *
     * @param message error message that need to be displayed.
     */
    public ftException(String message) {
        super(message);
    }
}
