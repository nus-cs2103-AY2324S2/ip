package duke.exceptions;

/**
 * Thrown when input is unrecognizeable or different types
 */
public class IncorrectFormatException extends DukeCeption {
    public IncorrectFormatException(String error) {
        super(error);
    }
}