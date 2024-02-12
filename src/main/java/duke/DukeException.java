package duke;

/**
 * The `DukeException` class represents any exception related to the Duke program.
 */
public class DukeException extends Exception {

    /**
     * Empty constructor of a `DukeException` object.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor with error message of a `DukeException` object.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
