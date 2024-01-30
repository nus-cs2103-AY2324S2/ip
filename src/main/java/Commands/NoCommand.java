package Commands;

import Task.TaskManager;
import UI.Ui;

public class NoCommand extends Command{
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        return true;
    }
}
