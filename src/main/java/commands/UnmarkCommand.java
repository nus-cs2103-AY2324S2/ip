package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command{
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        tasks.getTaskList().get(index - 1).unmarkDone();
        String result = "OK, I've marked this task as not done yet:\n" +
                tasks.getTaskList().get(index - 1);
        ui.showResult(result);
        storage.write(tasks.getTaskList());
    }
}
