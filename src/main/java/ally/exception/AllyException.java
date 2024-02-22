package ally.exception;

/**
 * Special Exception for Duke
 */
public class AllyException extends RuntimeException {
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
