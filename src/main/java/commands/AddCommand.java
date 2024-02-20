package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 *  Represents a generic command that adds a task to a user's tasklist
 *  and updates the user on their added task.
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        tasks.getTaskList().add(task);
        String result = "Got it. I've added this task:\n" + task
                + String.format("Now you have %d tasks in the list\n", tasks.getTaskList().size());
        storage.write(tasks.getTaskList());
        return result;
    }

    @Override
    public String showUsage() {
        return "";
    }
}
