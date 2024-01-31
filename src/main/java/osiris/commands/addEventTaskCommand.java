package osiris.commands;

import java.time.LocalDateTime;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public class addEventTaskCommand extends Command {

    public static final String COMMAND = "event";

    private final String taskName;

    private final LocalDateTime startDateTime;

    private final LocalDateTime endDateTime;

    public addEventTaskCommand(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
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
