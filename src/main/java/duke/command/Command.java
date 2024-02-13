package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a basic Command.
 */
public interface Command {
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public default boolean isExit() {
        return false;
    };
}
