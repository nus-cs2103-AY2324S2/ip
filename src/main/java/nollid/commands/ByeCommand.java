package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendGoodbyeMessage();
        System.exit(0);
    }
}
