package megatron.commands;

import megatron.data.exception.DukeException;
import megatron.storage.Storage;
import megatron.task.Task;
import megatron.task.TaskList;
import megatron.ui.Ui;

/**
 * Command subclass for deleting tasks
 */
public class DeleteCommand extends Command {

    /** Index to perform delete on */
    private final int deleteIndex;
    private Task toRemove;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            this.toRemove = tasks.deleteTask(deleteIndex);
            storage.saveTasks(tasks);
            return ui.deleteTask(toRemove, tasks.getNumTasks());
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.getIsUnDone()) {
            return ui.lastCommandUndoed();
        } else {
            new AddCommand(toRemove).execute(tasks, ui, storage);
            return ui.undoDelete(toRemove);
        }
    }

    @Override
    public boolean getIsUndoable() {
        return true;
    }
}
