package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 *  Handles the deletion of tasks from the user's task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public DeleteCommand() {
        this.index = 0;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        Task removedTask = tasks.getTaskList().get(index - 1);
        tasks.getTaskList().remove(index - 1);
        String result = "Noted. I've removed this task:\n"
                + removedTask
                + String.format("Now you have %d tasks in the list\n", tasks.getTaskList().size());
        storage.write(tasks.getTaskList());
        return result;
    }

    @Override
    public String showUsage() {
        return "Usage: Delete {index of task to be deleted}";
    }
}
