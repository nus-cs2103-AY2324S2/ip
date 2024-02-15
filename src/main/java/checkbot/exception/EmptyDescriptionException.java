package checkbot.exception;

/**
 * Represents an exception where the description of a task is empty.
 */
public class EmptyDescriptionException extends CheckbotException {
    public EmptyDescriptionException() {
        super("Sorry, the description of a task cannot be empty.");
    }
}
