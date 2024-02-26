package exceptions;

/**
 * The LeluExceptions class serves as the parent class for custom exceptions specific to the Lelu application.
 */
public class LeluException extends Exception {
    /**
     * Represents types of error from different tasks.
     */
    public enum ErrorType {
        TODO,
        EVENT,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        UPDATE
    }
    public LeluException(String message) {
        super(message);
    }
}
