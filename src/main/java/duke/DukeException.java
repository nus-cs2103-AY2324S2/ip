package duke;

import java.io.IOException;
/**
 * Represents an exception caused by Bingus.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
