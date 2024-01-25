public class NoDeadlineException extends IncompleteCommandException {

    public NoDeadlineException() {
        super("Deadline");
    }

    @Override
    public String toString() {
        return "OOPS!!! You have not specified a deadline.";
    }
}