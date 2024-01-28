public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super();
    }
    @Override
    public String toString() {
        return "Maybe you do not know what a deadline is,\nbut please have key in a valid time or day as a deadline following\n" +
                "a '/'\n";
    }
}
