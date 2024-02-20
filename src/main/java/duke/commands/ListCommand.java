package duke.commands;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command subclass for listing tasks
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks.getTasks());
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.nothingToUndo();
    }

    @Override
    public boolean getIsUndoable() {
        return false;
    }
}
