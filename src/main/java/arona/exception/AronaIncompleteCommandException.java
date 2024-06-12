package arona.exception;

/**
 * Represents an exception when the command argument is incomplete.
 *
 * @author Maximilliano Utomo
 */
public class AronaIncompleteCommandException extends AronaException {
    public AronaIncompleteCommandException(String message) {
        super("Sorry, sensei! You are missing the " + message + " argument (>.<)");
    }
}
