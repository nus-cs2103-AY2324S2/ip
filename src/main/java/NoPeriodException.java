public class NoPeriodException extends IncompleteCommandException {

    public NoPeriodException() {
        super("Event");
    }

    @Override
    public String toString() {
        return "OOPS!!! You have to specify a from and to period.";
    }
}