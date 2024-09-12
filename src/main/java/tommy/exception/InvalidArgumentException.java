package tommy.exception;

/**
 * Represents the exceptions thrown in the program related to invalid inputs.
 */
public class InvalidArgumentException extends TommyException {

    /**
     * Constructor for an InvalidArgumentException,
     * which initialises it with its error message.
     *
     * @param message String of where the invalid input is found.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String getErrorMessage() {
        String invalidArgumentPrefix = "Invalid Arguments - ";
        return invalidArgumentPrefix + getMessage();
    }
}
