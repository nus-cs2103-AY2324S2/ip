package snomexceptions;

public class InvalidCommandTaskDescException extends InvalidCommandException {
    public InvalidCommandTaskDescException() {
        super("Please do not leave your task description or date blank");
    }
}
