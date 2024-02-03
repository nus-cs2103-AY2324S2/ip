package duke.exceptions.parser;

import duke.exceptions.DukeException;

public class InvalidArgumentException extends DukeException {

    public InvalidArgumentException(String argName, String message) {
        super(String.format("Argument(s) '%s' %s.", argName, message));
    }
    public InvalidArgumentException(String argName, String message, String externalErrMsg) {
        super(String.format("Argument(s) '%s' %s. %s", argName, message, externalErrMsg));
    }
}
