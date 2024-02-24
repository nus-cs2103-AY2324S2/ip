package lucky.commands;

/**
 * The CommandException class is a custom exception class in Java that extends the Exception class and
 * allows for the creation of exceptions with a specific error message.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
