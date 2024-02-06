package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Handles the unmarking of task as done
 */
public class UnmarkTaskCommand extends Command{
    private final int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        tasks.getTaskList().get(index - 1).unmarkAsDone();
        String result = "OK, I've marked this task as not done yet:\n"
                + tasks.getTaskList().get(index - 1);
        ui.showResult(result);
        storage.write(tasks.getTaskList());
    }
}
