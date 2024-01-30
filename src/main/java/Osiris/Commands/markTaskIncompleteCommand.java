package Osiris.Commands;

import Osiris.Task.TaskManager;
import Osiris.UI.Ui;

public class markTaskIncompleteCommand extends Command{

    public static final String COMMAND = "unmark";

    private int taskIndex;

    public markTaskIncompleteCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.markTaskIncomplete(this.taskIndex - 1);
        if (isSuccess) {
            userInterface.markTaskIncompleteSuccessNotification(taskManager.getTask(this.taskIndex - 1).toString());
        }
        return true;
    }
}
