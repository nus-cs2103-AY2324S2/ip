package duke.exceptions;

/**
 * The DukeIOException class represents exceptions specific to input/output errors in the Duke chatbot application.
 * It extends the DukeException class.
 */
public class DukeIOException extends DukeException {

    /**
     * Constructs a new DukeIOException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeIOException(String errorMessage) {
        super(errorMessage);
    }
}
