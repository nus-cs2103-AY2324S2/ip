package megatron.commands;

import megatron.data.exception.DukeException;
import megatron.storage.Storage;
import megatron.task.Task;
import megatron.task.TaskList;
import megatron.ui.Ui;

/**
 * Command subclass for adding tasks
 */
public class AddCommand extends Command {
    private final Task newTask; /** Task to add */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.addTask(newTask);
            storage.saveTasks(tasks);
            return ui.addTask(newTask, tasks.getNumTasks());
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.getIsUnDone()) {
            return ui.lastCommandUndoed();
        } else {
            super.setUnDone();
            new DeleteCommand(tasks.getNumTasks()).execute(tasks, ui, storage);
            return ui.undoAdd(newTask);
        }
    }

    @Override
    public boolean getIsUndoable() {
        return true;
    }
}
