package megatron.commands;

import megatron.data.exception.DukeException;
import megatron.storage.Storage;
import megatron.task.Task;
import megatron.task.TaskList;
import megatron.ui.Ui;

/**
 * Command subclass for marking or unmarking tasks
 */
public class MarkCommand extends Command {

    /** Index to mark or unmark */
    private final int updateIndex;
    /** Denotes whether to mark or unmark task */
    private boolean isComplete;

    /**
     * Constructor of MarkCommand
     * @param updateIndex task to update
     * @param isComplete whether to mark as done or not done
     */
    public MarkCommand(int updateIndex, boolean isComplete) {
        this.updateIndex = updateIndex;
        this.isComplete = isComplete;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task updateTask = tasks.markTask(updateIndex, isComplete);
            storage.saveTasks(tasks);
            return ui.mark(updateTask, isComplete);

        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.getIsUnDone()) {
            return ui.lastCommandUndoed();
        } else {
            this.isComplete = !isComplete;
            this.execute(tasks, ui, storage);
            return ui.undoMark(isComplete);
        }
    }

    @Override
    public boolean getIsUndoable() {
        return true;
    }
}
