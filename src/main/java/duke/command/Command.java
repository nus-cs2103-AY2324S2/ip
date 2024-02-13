package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a basic Command.
 */
public interface Command {
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException;

    public default boolean isExit() {
        return false;
    };

    public default boolean isExpenseCommand() {
        return false;
    }
}
