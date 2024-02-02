package duke.exceptions;

/**
 * Thrown when description is empty
 */
public class EmptyDescriptionException extends DukeCeption {
    public EmptyDescriptionException(String error) {
        super(error);
    }
}
