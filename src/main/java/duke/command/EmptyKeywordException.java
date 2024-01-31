package duke.command;

import duke.DukeException;

public class EmptyKeywordException extends DukeException {
    public EmptyKeywordException(String message, String botMessage) {
        super(message, botMessage);
    }
}
