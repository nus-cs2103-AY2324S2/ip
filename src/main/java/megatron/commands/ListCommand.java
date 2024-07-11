package megatron.commands;

import megatron.data.exception.DukeException;
import megatron.storage.Storage;
import megatron.task.TaskList;
import megatron.ui.Ui;

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
