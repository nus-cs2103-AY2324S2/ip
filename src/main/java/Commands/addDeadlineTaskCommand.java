package Commands;

import Task.TaskManager;
import UI.Ui;

public class addDeadlineTaskCommand extends Command{

    public static final String COMMAND = "deadline";

    private String taskName;

    private String deadline;

    public addDeadlineTaskCommand(String taskName, String deadline ){
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.addDeadlineTask(this.taskName, this.deadline, false);
        if (isSuccess) {
            userInterface.addDeadlineTaskSuccessNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
