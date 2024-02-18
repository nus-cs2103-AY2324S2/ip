package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

public class GuideCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTutorial();
    }
}
