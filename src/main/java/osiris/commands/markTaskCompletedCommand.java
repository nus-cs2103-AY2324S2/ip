package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public class markTaskCompletedCommand extends Command {

    public static final String COMMAND = "mark";

    private final int taskIndex;

    public markTaskCompletedCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.markTaskCompleted(this.taskIndex - 1);
        if (isSuccess) {
            userInterface.markTaskCompletedSuccessNotification(taskManager.getTask(this.taskIndex - 1).toString());
        }
        return true;
    }
}
