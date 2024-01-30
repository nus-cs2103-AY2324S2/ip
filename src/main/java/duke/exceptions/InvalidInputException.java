package duke.exceptions;

import duke.TextTemplate;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super(TextTemplate.INVALID_COMMAND);
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
