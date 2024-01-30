package Osiris.Commands;

import Osiris.Task.TaskManager;
import Osiris.UI.Ui;

public class UnsupportedCommand extends Command{
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        userInterface.unsupportedCommandsOutput();
        return true;
    }
}
