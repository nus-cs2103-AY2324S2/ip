package duke.exceptions;

/**
 * The MissingArgumentException class provides an exception for invalid
 * arguments in the duke application
 *
 * @author Ryan NgWH
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Creates am InvalidArgumentException
     *
     * @param errorMessage Error message
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
