package alastor.command;

import alastor.AlastorException;
import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;
import alastor.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    protected Task task;

    /**
     * Constructs an AddCommand.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlastorException {
        tasks.add(task);
        ui.showAdd(task, tasks);
        storage.saveAdd(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
