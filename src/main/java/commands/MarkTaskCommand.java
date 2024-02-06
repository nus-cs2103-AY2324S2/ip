package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Handles the Marking of Task as done.
 */
public class MarkTaskCommand extends Command{
    private final int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        tasks.getTaskList().get(index - 1).markDone();
        String result = "Nice! I've marked this task as done:\n" +
                tasks.getTaskList().get(index - 1);
        ui.showResult(result);
        storage.write(tasks.getTaskList());
    }
}
