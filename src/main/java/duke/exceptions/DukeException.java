package duke.exceptions;

/**
 * The DukeException class represents exceptions specific to the Duke chatbot application.
 * It extends the RuntimeException class.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
