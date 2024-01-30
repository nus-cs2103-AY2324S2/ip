package Commands;

import Task.TaskManager;
import UI.Ui;

public class UnsupportedCommand extends Command{
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        userInterface.unsupportedCommandsOutput();
        return true;
    }
}
