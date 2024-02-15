package duke.exceptions;

/**
 * The DukeDuplicateException class represents exceptions specific
 * to invalid arguments in the Duke chatbot application.
 * It extends the DukeException class.
 */
public class DukeDuplicateException extends DukeException {

    /**
     * Constructs a new DukeDuplicateException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeDuplicateException(String errorMessage) {
        super(errorMessage);
    }
}
