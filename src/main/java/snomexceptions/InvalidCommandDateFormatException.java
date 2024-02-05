package snomexceptions;


public class InvalidCommandDateFormatException extends InvalidCommandException {
    public InvalidCommandDateFormatException() {
        super("Please ensure that your date(s) is of the format yyyy-mm-dd");
    }
}
