package duke.exceptions;

/**
 * Thrown when data loaded cannot be read
 */
public class LoadDataCorruptedException extends DukeCeption {
    public LoadDataCorruptedException(String error) {
        super(error);
    }
}