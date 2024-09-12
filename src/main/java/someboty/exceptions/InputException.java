package someboty.exceptions;

/**
 * Handles errors caused by user inputs.
 */
public class InputException extends RuntimeException{
    public InputException(String errorMessage) {
        super(errorMessage);
    }
}
