package duke;

/**
 * A DukeException class to handle exception messages.
 */
public class DukeException extends Exception{

    /**
     * A constructor to create new DukeException.
     *
     * @param message The message of the DukeException.
     */
    public DukeException(String message) {
        super(message);
    }
}