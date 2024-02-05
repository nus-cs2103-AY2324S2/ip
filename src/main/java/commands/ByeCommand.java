package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
        System.exit(0);
    }
}
