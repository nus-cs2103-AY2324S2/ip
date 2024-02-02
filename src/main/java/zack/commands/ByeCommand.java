package zack.commands;

import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
