package sylvia.command;

import sylvia.SylviaException;

/**
 * Represents an exception that occurs when the user enters an unparsable
 * command.
 */
public class UnknownCommandException extends SylviaException {
    public UnknownCommandException(String message, String botMessage) {
        super(message, botMessage);
    }
}
