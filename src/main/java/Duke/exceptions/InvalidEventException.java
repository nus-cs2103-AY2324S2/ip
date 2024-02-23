package duke.exceptions;

/**
 * Class for InvalidEventException
 */
public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super();
    }

    @Override
    public String toString() {
        return "After I try so hard, you want to cook me by keying the date\n"
                + "and time wrongly try again, by adding the event in the format:\n"
                + "event [task description] /from: dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm\n"
                + "/from date must be before /to date\n";
    }
}
