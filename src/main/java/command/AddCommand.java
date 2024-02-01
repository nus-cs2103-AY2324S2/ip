package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents Command that adds task to task list.
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Creates AddCommand Object with specified Task to add.
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showAdd(this.task, tasks.size());
    }
}
