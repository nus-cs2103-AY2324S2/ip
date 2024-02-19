package shuheng.exceptions;

/**
 * This class represents the error thrown for a invalid user input.
 */
public class InvalidInputException extends DukeException {
    /**
     * This is a constructor for the exception.
     *
     * @param msg The message to be thrown.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }
}
