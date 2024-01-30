package Osiris.Commands;

import Osiris.Task.Task;
import Osiris.Task.TaskManager;
import Osiris.UI.Ui;

public class removeTaskCommand extends Command{

    public static final String COMMAND = "delete";

    private int taskIndex;

    public removeTaskCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        Task removedTask = taskManager.removeTask(this.taskIndex - 1);
        if (removedTask != null) {
            userInterface.removeTaskSuccessNotification(removedTask.toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
