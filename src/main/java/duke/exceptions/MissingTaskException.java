package duke.exceptions;

/**
 * The MissingTaskException class provides an exception for missing
 * tasks in the duke exception.
 *
 * @author Ryan NgWH
 */

public class MissingTaskException extends DukeException {
    /**
     * Creates a MissingTaskException
     *
     * @param errorMessage Error message
     */
    public MissingTaskException(String errorMessage) {
        super(errorMessage);
    }
}
