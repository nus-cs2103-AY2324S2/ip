package teemo;
/**
 * Custom Exception for Duke.
 */
public class DukeException extends Exception {
    /**
     * Default Constructor.
     */
    public DukeException() { }
    /**
     * Overloaded Constructor
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
