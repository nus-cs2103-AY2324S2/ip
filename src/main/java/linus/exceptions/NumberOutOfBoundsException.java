package linus.exceptions;

/**
 * Thrown when trying to access an index that is not in the list
 */
public class NumberOutOfBoundsException extends LinusCeption {
    public NumberOutOfBoundsException(String error) {
        super(error);
    }
}
