package duke;

/**
 * Represents an exception thrown when users provide an incorrectly formatted date
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructs a new InvalidDateFormatException with a message telling the user the correct format
     */
    public InvalidDateFormatException() {
        super("Invalid DateTime Format! Please use yyyy-mm-dd");
    }
}
