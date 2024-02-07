package duke.exception;

/**
 * Exception thrown when user enters an input that is not within
 * the list of commands recognised by the bot
 */
public class UnknownInputException extends Exception {
    public UnknownInputException() {
        super("Sorry, I don't know what that command means");
    }
}
