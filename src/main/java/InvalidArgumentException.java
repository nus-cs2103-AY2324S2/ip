public class InvalidArgumentException extends DukeException{
    //Used to indicate that an argument for the command is invalid
    public InvalidArgumentException(String message) {
        super(message);
    }
}
