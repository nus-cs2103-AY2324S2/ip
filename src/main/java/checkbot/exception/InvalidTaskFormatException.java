package checkbot.exception;

/**
 * Represents an exception where the file does not follow the required format.
 */
public class InvalidTaskFormatException extends CheckbotException {
    public InvalidTaskFormatException() {
        super("File does not follow the required format");
    }
}
