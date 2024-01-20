public class InvalidInputException extends DukeException {
    public InvalidInputException(String displayMessage) {
        super("Users have entered invalid input values", displayMessage);
    }

}
