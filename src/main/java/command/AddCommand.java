package command;

import dook.DookException;
import dook.Storage;
import task.Task;
import task.TaskList;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds given task to the task list.
     *
     * @param tasks Target tasklist to add the task to.
     * @param storage The storage interface.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DookException {
        String toReturn = "";
        tasks.addTask(task);
        toReturn = "Oki! I've added this task:\n";
        toReturn += task.toString() + "\n";
        toReturn += tasks.getStatus();
        storage.write(tasks);
        return toReturn;
    }
}
