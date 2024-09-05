package aaron.exception;

/**
 * class that represents an exception when the user inputs an invalid command
 * format
 */
public class InvalidCommandFormatException extends AaronBotException {
    public InvalidCommandFormatException(String e) {
        super(e);
    }

}
