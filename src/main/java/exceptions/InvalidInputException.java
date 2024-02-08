package exceptions;

/**
 *  Represents exception relating to user submitting incorrect command inputs with
 *  wrong parameters.
 */
public class InvalidInputException extends BlawgException {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
