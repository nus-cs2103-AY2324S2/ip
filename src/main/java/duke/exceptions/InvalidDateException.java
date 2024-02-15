package duke.exceptions;

/**
 * The InvalidDateException class represents an exception indicating an invalid date format.
 * <p>
 * This exception is thrown when a date input by the user does not match the expected format.
 * </p>
 * <p>
 * It extends the DukeException class.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.exceptions.DukeException
 */
public class InvalidDateException extends DukeException{

    /**
     * Constructs a new InvalidDateException with a default message.
     */
    public InvalidDateException() {
        super("Invalid date format. Please use dd-MM-yyyy");
    }
}
