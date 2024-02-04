package shon.command;

import shon.TaskList;
import shon.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
