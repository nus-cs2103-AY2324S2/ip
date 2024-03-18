package bytetalker.exception;

/**
 * Exception for unsupported task.
 * If the user inputs unsupported command format, this exception will be thrown.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class UnsupportedCommandException extends ByteTalkerException {
    public UnsupportedCommandException(String errorMessage) {
        super(errorMessage);
    }
}
