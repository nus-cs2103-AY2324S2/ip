package duke.exception;
public class UnknownInputException extends Exception {
    public UnknownInputException() {
        super("Sorry, I don't know what that command means");
    }
}
