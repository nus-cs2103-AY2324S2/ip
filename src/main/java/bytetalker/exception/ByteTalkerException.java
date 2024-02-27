package bytetalker.exception;

/**
 * Represents all exceptions that can be caused during program execution.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class ByteTalkerException extends Exception {

    public ByteTalkerException() {

    }

    public ByteTalkerException(String errorMessage) {
        super(errorMessage);
    }
}
