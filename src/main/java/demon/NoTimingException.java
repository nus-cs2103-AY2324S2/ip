package demon;

/**
 * An exception class that will be thrown when no timing is specified by the user when creating new task.
 */
public class NoTimingException extends Exception {
    public NoTimingException(String msg) {
        super(msg);
    }

}
