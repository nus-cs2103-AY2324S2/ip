package duke.commands;

import duke.DukeException;

public class DukeCommandNotFoundException extends DukeException {
    public DukeCommandNotFoundException() {
        super("command not found");
    }
}
