package duke.exceptions;
public class InvalidDateException extends DukeException{
    public InvalidDateException() {
        super("Invalid date format. Please use dd-MM-yyyy");
    }
}
