package duke.exceptions;

/**
 * Thrown when trying to access an index that is not in the list
 */
public class NumberOutOfBoundsException extends DukeCeption {
    public NumberOutOfBoundsException(String error) {
        super(error);
    }
}
