package command;

import task.Task;
import task.TaskList;
import dook.Ui;
import dook.Storage;
import dook.DookException;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        String toReturn = "";
        tasks.addTask(task);
        toReturn = "Oki! I've added this task:\n";
        toReturn += task.toString() + "\n";
        toReturn += tasks.getStatus();
        storage.write(tasks);
        return toReturn;
    }
}