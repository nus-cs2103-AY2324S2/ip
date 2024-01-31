package duke.exceptions;

/**
 * The DukeUnknownCommandException class represents exceptions specific to unknown commands in the Duke chatbot application.
 * It extends the DukeException class.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Constructs a new DukeUnknownCommandException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeUnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
