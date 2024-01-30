package duke.command;

import duke.DukeException;

public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(String message, String botMessage) {
        super(message, botMessage);
    }
}
