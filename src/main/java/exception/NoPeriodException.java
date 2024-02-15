package exception;

/**
 * Represents an exception when a period is not specified.
 */
public class NoPeriodException extends IncompleteCommandException {
    /**
     * Constructor for NoPeriodException.
     */
    public NoPeriodException() {
        super("Event");
    }

    /**
     * Returns the error message when a period is not specified.
     */
    @Override
    public String toString() {
        return "OOPS!!! You have to specify a from and to period.";
    }
}
