package Duke.exceptions;
public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super();
    }
    @Override
    public String toString() {
        return "Date format should be dd/mm/yyyy HHmm\n";
    }
}
