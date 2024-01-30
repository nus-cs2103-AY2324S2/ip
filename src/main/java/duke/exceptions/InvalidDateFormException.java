package duke.exceptions;

import duke.TextTemplate;

public class InvalidDateFormException extends Exception {
    public InvalidDateFormException() {
        super(TextTemplate.INVALID_DATETIME);
    }

    public InvalidDateFormException(String message) {
        super(message);
    }
}
