package aaron.exception;

/**
 * class that represents an exception that occurs during the execution of a
 * user-aaronbot interaction
 */
public class AaronBotException extends Exception {
    public AaronBotException(String e) {
        super(e);
    }
}