package panda.exception;

public class InvalidFormatException extends PandaException {
    /**
     * Constructs a new InvalidFormatException with a default message.
     */
    public InvalidFormatException() {
        super("OOPS! Please use the correct format for this command.");
    }
}
