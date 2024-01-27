package duke.exceptions;

/**
 * The MissingArgumentException class provides an exception.
 *
 * @author Ryan NgWH
 */
public class MissingArgumentException extends DukeException {
    /**
     * Constructor for a MissingArgumentException
     *
     * @param errorMessage Error message
     */
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
