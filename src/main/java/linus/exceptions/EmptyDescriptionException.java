package linus.exceptions;

/**
 * Thrown when description is empty
 */
public class EmptyDescriptionException extends LinusCeption {
    public EmptyDescriptionException(String error) {
        super(error);
    }
}
