package duke.exceptions;

public class DukeException extends Exception {
    public DukeException() {
        super() ;
    }

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
