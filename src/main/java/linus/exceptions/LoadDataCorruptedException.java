package linus.exceptions;

/**
 * Thrown when data loaded cannot be read
 */
public class LoadDataCorruptedException extends LinusCeption {
    public LoadDataCorruptedException(String error) {
        super(error);
    }
}