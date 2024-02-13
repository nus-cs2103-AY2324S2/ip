package command;

import storage.Storage;
import task.Task;
import task.TaskList;

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
        assert task != null;
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(this.task);
        String response = "";
        response += "I've added the following task:\n" + task + "\nYou have " + tasks.size() + " tasks";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
