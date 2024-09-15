package demon;

/**
 * An exception class that will be thrown when description of a task is not provided from the input.
 */
public class ExcessArgumentException extends DemonException {
    public ExcessArgumentException(String msg) {
        super(msg);
    }

}
