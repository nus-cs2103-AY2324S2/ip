package duke;

/**
 * DukeException to handle special cases of exception thrown by the duke chatbot.
 */
public class DukeException extends Exception {
    /**
     * Initialises the exception.
     *
     * @param msg exception message to figure out what is wrong.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
