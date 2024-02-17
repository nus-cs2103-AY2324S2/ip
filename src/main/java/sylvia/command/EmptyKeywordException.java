package sylvia.command;

import sylvia.SylviaException;

/**
 * Represents an exception that occurs when the keyword of a command is empty.
 */
public class EmptyKeywordException extends SylviaException {
    public EmptyKeywordException(String message, String botMessage) {
        super(message, botMessage);
    }
}
