package duke.commons.exceptions;

/**
 * Custom exception for handling application-specific exceptions in Duke.
 * 
 * @param msg Mesage that explains reason for exception.
 */

public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
