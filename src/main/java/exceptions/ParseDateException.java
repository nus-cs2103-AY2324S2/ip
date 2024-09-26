package exceptions;

/**
 * This class signals that a String failed to get parsed into
 * a LocalDate.
 */
public class ParseDateException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraints
     */
    public ParseDateException(String message) {
        super(message);
    }
}
