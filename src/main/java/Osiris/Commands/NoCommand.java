package Osiris.Commands;

import Osiris.Task.TaskManager;
import Osiris.UI.Ui;

public class NoCommand extends Command{
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        return true;
    }
}
