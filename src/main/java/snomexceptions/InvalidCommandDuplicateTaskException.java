package snomexceptions;


public class InvalidCommandDuplicateTaskException extends InvalidCommandException {
    public InvalidCommandDuplicateTaskException() {
        super("There cannot be duplicate tasks");
    }
}