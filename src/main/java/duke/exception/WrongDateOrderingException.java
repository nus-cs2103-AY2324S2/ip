package duke.exception;

<<<<<<< HEAD
/**
 * Represents an exception thrown when the ordering of dates is incorrect.
 */
public class WrongDateOrderingException extends DukeException {

    /**
     * Constructs a WrongDateOrderingException with no specified detail message.
     */
    public WrongDateOrderingException() {
        super();
    }

    /**
     * Gets a detailed error message for the exception.
     *
     * @return A string representing the detailed error message.
     */
=======
public class WrongDateOrderingException extends DukeException {
    public WrongDateOrderingException() {
        super();
    }
>>>>>>> A-CodingStandard
    @Override
    public String getMessage() {
        return super.getMessage() + "the 'to' date is after the 'from' date.";
    }
}

