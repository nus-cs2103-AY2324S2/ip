package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Handles the Marking of Task as done.
 */
public class MarkTaskCommand extends Command {
    private final int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    public MarkTaskCommand() {
        this.index = 0;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        tasks.getTaskList().get(index - 1).markAsDone();
        String result = "Nice! I've marked this task as done:\n"
                + tasks.getTaskList().get(index - 1);
        storage.write(tasks.getTaskList());
        return result;
    }

    @Override
    public String showUsage() {
        return "Usage: Mark {index of task to be marked}";
    }
}
