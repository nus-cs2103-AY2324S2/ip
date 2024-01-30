package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public class UnsupportedCommand extends Command{
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        userInterface.unsupportedCommandsOutput();
        return true;
    }
}
