package duke.exception;

import duke.exception.DukeException;

public class WrongDateFormatException extends DukeException{
    public WrongDateFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "the 'date' format is wrong.";
    }

}
