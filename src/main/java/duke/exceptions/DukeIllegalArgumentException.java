package duke.exceptions;

/**
 * The DukeIllegalArgumentException class represents exceptions specific to invalid arguments in the Duke chatbot application.
 * It extends the DukeException class.
 */
public class DukeIllegalArgumentException extends DukeException {

    /**
     * Constructs a new DukeIllegalArgumentException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeIllegalArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
