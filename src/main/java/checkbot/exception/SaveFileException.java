package checkbot.exception;

/**
 * Represents an exception where Checkbot is unable to save the user's todo list
 * as a file.
 */
public class SaveFileException extends CheckbotException {
    public SaveFileException() {
        super("I am unable to save your todo list as a file. Your changes were not saved.");
    }
}
