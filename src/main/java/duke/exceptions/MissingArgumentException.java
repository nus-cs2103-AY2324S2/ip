package duke.exceptions;

/**
 * The MissingArgumentException class provides an exception for missing
 * arguments in the duke exception.
 *
 * @author Ryan NgWH
 */
public class MissingArgumentException extends DukeException {
    /**
     * Creates a MissingArgumentException
     *
     * @param errorMessage Error message
     */
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
