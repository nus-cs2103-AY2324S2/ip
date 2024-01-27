package duke.exception;

import duke.exception.DukeException;

/**
 * Exception for from time later than to time.
 */
public class TimeInconsistException extends DukeException {

    /**
     * Constructor for this exception.
     */
    public TimeInconsistException() {
        super();
    }

    /**
     * Give error message.
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return String.format("%s your [to] should not before [from]", super.getMessage());
    }
}
