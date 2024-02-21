package arona.exception;

/**
 * Represents an exception when the date given in the command is invalid.
 *
 * @author Maximilliano Utomo
 */
public class AronaInvalidDateException extends AronaException {
    public AronaInvalidDateException(String message) {
        super("Sorry, sensei! Dates must be of the form yyyy-mm-dd (>.<)");
    }
}
