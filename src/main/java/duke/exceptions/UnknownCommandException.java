package duke.exceptions;

/**
 * Thrown when trying to use an unknown command
 */
public class UnknownCommandException extends DukeCeption {
    public UnknownCommandException(String error) {
        super(error);
    }
}
