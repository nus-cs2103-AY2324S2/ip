package judy.exceptions;

/**
 * InvalidTaskIndexException is a specific type of Judy Exception that shows
 * an error when invalid task index is entered by users.
 */
public class InvalidTaskIndexException extends JudyException {

    /**
     * Constructs an InvalidTaskIndexException to show error message
     * and direct users to list out their task wo see what they have.
     */
    public InvalidTaskIndexException() {
        super("Invalid task index. Type 'list' to list out your tasks. ");
    }
}
