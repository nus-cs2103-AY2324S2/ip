package duke.exceptions;

/**
 * The InvalidCommandException class provides an exception for invalid
 * commands in the duke application
 *
 * @author Ryan NgWH
 */
public class InvalidCommandException extends DukeException {
    /**
     * Creates a InvalidCommandException
     *
     * @param errorMessage Error message
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
