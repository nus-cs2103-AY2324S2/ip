package duke.exception;

/**
 * Exception for all kinds of buggy inputs
 */
public class DukeException extends Exception{

    /**
     * Constructor
     */
    public DukeException() {
        super();
    }

    /**
     * Print this message when error caught.
     * @return A starter of every error reply.
     */
    @Override
    public String getMessage() {
        return String.format("err... Please accept my sincere apologies, but");
    }
}
