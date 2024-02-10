package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.Ui;

/**
 * A command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Construct a command that deletes a task at the given index.
     */
    public DeleteCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "The task list must not be null";
        assert ui != null : "The ui must not be null";
        assert storage != null : "The storage must not be null";

        try {
            tasks.deleteTask(index);
        } catch (TaskList.TaskNotFound e) {
            ui.showError(e);
        }
    }
}
