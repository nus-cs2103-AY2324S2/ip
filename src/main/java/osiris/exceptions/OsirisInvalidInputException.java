package osiris.exceptions;

/**
 * The OsirisInvalidInputException class represents an exception related to invalid user input.
 */
public class OsirisInvalidInputException extends OsirisException {

    /**
     * Constructs a new OsirisInvalidInputException with the specified error message.
     *
     * @param s The error message indicating the nature of the invalid input.
     */
    public OsirisInvalidInputException(String s) {
        super(s);
    }
}
