public class InvalidInputFormatException extends DukeException{
    public InvalidInputFormatException(
                                       String displayMessage) {
        super("Users have entered the command in invalid format", displayMessage);
    }
}
