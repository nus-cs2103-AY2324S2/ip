public class InvalidCommandException extends DukeException{
    //Used to indicate that the command is invalid
    public InvalidCommandException(String message) {
        super(message);
    }
}
