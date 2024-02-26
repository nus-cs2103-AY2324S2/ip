package duke;

/**
 * The type Duke exception.
 * This Exception class is used within Duke application
 */
public class DukeException extends Exception{
    /**
     * Instantiates a new Duke exception.
     *
     * @param errMessage the error message
     */
    public DukeException(String errMessage) {
        super(errMessage);
    }
}
