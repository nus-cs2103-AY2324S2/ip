package ben.commands;

import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
