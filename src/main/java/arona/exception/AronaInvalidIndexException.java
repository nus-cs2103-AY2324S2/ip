package arona.exception;

/**
 * Represents an exception when the index provided is invalid.
 *
 * @author Maximilliano Utomo
 */
public class AronaInvalidIndexException extends AronaException {
    public AronaInvalidIndexException(String message) {
        super("Sorry, sensei! You only have " + message + " task"
                + (Integer.parseInt(message) == 1 ? "" : "s")
                + ". Please choose a valid task number (>.<)");
    }
}
