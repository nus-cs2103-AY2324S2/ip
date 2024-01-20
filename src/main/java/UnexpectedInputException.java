public class UnexpectedInputException extends DukeException{
    public UnexpectedInputException(
                                    String displayMessage) {
        super("Users have entered extra or unexpected inputs", displayMessage);
    }
}
