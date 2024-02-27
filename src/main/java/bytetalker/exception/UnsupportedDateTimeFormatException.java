package bytetalker.exception;

/**
 * Exception when user input format for DateTime is wrong.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class UnsupportedDateTimeFormatException extends ByteTalkerException {
    public UnsupportedDateTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
