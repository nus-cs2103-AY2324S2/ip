package arona.exception;

/**
 * Represents an exception when the command is invalid.
 *
 * @author Maximilliano Utomo
 */
public class AronaInvalidCommandException extends AronaException {
    public AronaInvalidCommandException(String message) {
        super("Sorry, sensei! I don't understand your message (>_<)");
    }
}
