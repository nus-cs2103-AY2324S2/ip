package duke.exception;

/**
 * Represents the exceptions thrown in the program related to invalid inputs.
 */
public class InvalidArgumentException extends DukeException{
    /**
     * Constructor for an InvalidArgumentException,
     * which initialises it with its error message.
     *
     * @param message String of where the invalid input is found.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String errorMessage() {
        return "Invalid Arguments - " + getMessage();
    }
}
