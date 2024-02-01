package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class InvalidCommand extends Command{
    public final String result;

    public InvalidCommand(String result) {
        this.result = result;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showResult(result);
    }
}
