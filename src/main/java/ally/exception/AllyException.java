package ally.exception;

/**
 * Special Exception for Duke
 */
public class AllyException extends RuntimeException {

    String deadlineFormat = "deadline name-of-event /by YYYY-MM-DD";
    String eventFormat = "event name-of-event ";
    /**
     * Constructor for AllyException.
     */
    public AllyException() {
        super();
    }

    @Override
    public String getMessage() {
        return "I don't understand you sorry!";
    }
}
