package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveToDisk(taskList);
    }
}
