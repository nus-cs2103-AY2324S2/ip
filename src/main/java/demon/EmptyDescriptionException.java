package demon;

/**
 * An exception class that will be thrown when description of a task is not provided from the input.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String msg) {
        super(msg);
    }

}
