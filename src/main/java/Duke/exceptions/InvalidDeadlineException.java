package duke.exceptions;

/**
 * Class for InvalidDeadlineException
 */
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super();
    }
    @Override
    public String toString() {
        return "Maybe you do not know what a deadline is but key in deadline "
                + "in this format:\n"
                + "deadline [description] /by dd/mm/yyyy HHmm\n";
    }
}
