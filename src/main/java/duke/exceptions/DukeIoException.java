package duke.exceptions;

/**
 * The DukeIoException class represents exceptions specific to input/output errors in the Duke chatbot application.
 * It extends the DukeException class.
 */
public class DukeIoException extends DukeException {

    /**
     * Constructs a new DukeIOException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeIoException(String errorMessage) {
        super(errorMessage);
    }
}
