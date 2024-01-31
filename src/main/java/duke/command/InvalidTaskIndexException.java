package duke.command;

import duke.DukeException;

/**
 * Represents an exception that occurs when the index provided by the user is
 * invalid.
 */
public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(String message, String botMessage) {
        super(message, botMessage);
    }
}
