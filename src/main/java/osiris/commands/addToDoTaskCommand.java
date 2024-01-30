package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public class addToDoTaskCommand extends Command{

    public static final String COMMAND = "todo";

    private String taskName;

    public addToDoTaskCommand(String taskName){
        this.taskName = taskName;
    }
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.addToDoTask(this.taskName, false);
        if (isSuccess) {
            userInterface.addToDoTaskSuccessNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
