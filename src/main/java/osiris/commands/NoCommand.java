package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public class NoCommand extends Command{
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        return true;
    }
}
