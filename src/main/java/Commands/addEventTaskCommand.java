package Commands;

import Task.TaskManager;
import UI.Ui;

public class addEventTaskCommand extends Command{

    public static final String COMMAND = "event";

    private String taskName;

    private String startDateTime;

    private String endDateTime;

    public addEventTaskCommand(String taskName, String startDateTime, String endDateTime) {
        this.taskName = taskName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.addEventTask(this.taskName, this.startDateTime, this.endDateTime, false);

        if (isSuccess) {
            userInterface.addEventTaskSuccessNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
