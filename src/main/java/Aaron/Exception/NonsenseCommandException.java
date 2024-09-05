package aaron.exception;

/**
 * class that represents an exception when user inputs a nonsense command
 * (not recognizable)
 */
public class NonsenseCommandException extends AaronBotException {
    public NonsenseCommandException(String e) {
        super(e);
    }
}
