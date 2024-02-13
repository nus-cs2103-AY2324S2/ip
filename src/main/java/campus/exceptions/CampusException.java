package campus.exceptions;

/**
 * Exception class for the Campus Class, handles in usually throwing errors with a specified message that details
 * the cause of the error.
 */
public class CampusException extends Exception {
    public CampusException() {
        super("An error has occurred");
    }
    public CampusException(String s) {
        super(s);
    }
}
