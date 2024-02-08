package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        return null;
    }
}
