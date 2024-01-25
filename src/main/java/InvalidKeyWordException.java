public class InvalidKeyWordException extends DukeException{
    public InvalidKeyWordException(String displayMessage) {
        super("Users have entered invalid keyword", displayMessage);
    }
}
