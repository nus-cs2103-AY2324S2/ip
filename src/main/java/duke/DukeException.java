package duke;

/**
 * Custom exception class for Duke application.
 * Indicates an exception specific to the Duke application.
 */
public class DukeException extends Exception {
    private String msg;

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public DukeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Retrieves the error message associated with the exception.
     *
     * @return The error message.
     */
    public String getMsg() {
        return msg;
    }
}
