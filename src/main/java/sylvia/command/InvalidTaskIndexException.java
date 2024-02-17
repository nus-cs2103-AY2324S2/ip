package sylvia.command;

import sylvia.SylviaException;

/**
 * Represents an exception that occurs when the index provided by the user is
 * invalid.
 */
public class InvalidTaskIndexException extends SylviaException {
    public InvalidTaskIndexException(String message, String botMessage) {
        super(message, botMessage);
    }
}
