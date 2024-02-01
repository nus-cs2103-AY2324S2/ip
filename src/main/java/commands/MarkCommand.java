package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class MarkCommand extends Command{
    private final int index;

    public MarkCommand(int index) {
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
