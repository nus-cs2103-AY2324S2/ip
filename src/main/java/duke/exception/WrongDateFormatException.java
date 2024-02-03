package duke.exception;

<<<<<<< HEAD
/**
 * Represents an exception thrown when the date format is incorrect.
 */
public class WrongDateFormatException extends DukeException {

    /**
     * Constructs a WrongDateFormatException with no specified detail message.
     */
=======

public class WrongDateFormatException extends DukeException {
>>>>>>> A-CodingStandard
    public WrongDateFormatException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "the 'date' format is wrong.";
    }
}

