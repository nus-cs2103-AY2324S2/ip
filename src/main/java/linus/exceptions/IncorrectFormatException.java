package linus.exceptions;

/**
 * Thrown when input is unrecognizeable or different types
 */
public class IncorrectFormatException extends LinusCeption {
    public IncorrectFormatException(String error) {
        super(error);
    }
}