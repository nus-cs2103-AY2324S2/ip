package raphael.exception;

/**
 * An exception class for duplicate tasks.
 */
public class DuplicateTaskException extends RaphaelException {
    public DuplicateTaskException() {
        super(Type.DUPLICATE_TASK_EXCEPTION);
    }
}
