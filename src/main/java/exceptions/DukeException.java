package exceptions;

/**
 * Exceptions local this application, requires a message to be provided
 */
public class DukeException extends Exception{

    public DukeException() {
        super();
    }
    public DukeException(String message) {
        super(message);
    }
}
