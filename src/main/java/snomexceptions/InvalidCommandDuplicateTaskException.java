package snomexceptions;

/**
 * This is the exception thrown when the user enters a duplicate task into Snom.
 */
public class InvalidCommandDuplicateTaskException extends InvalidCommandException {
    public InvalidCommandDuplicateTaskException() {
        super("There cannot be duplicate tasks");
    }
}
