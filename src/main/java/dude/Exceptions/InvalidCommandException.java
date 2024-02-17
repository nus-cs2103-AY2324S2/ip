package dude.Exceptions;

public class InvalidCommandException extends DudeException {
    //Used to indicate that the command is invalid
    public InvalidCommandException(String message) {
        super(message);
    }
}
