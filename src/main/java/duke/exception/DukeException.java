package duke.exception;
public class DukeException extends Exception{
    public DukeException() {
        super();
    }
    public String getMessage() {
        return "Unfortunately ";
    }
}