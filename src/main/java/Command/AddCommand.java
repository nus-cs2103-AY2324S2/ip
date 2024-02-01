package Command;

import Task.Task;
import Task.TaskList;
import Dook.Ui;
import Dook.Storage;
import Dook.DookException;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds given task to the task list.
     *
     * @param tasks Target tasklist to add the task to.
     * @param ui The user interface.
     * @param storage The storage interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        tasks.addTask(task);
        ui.println("Oki! I've added this task:");
        ui.println(task.toString());
        tasks.printStatus();
        storage.write(tasks);
    }
}