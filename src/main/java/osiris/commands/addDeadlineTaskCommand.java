package osiris.commands;

import java.time.LocalDate;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public class addDeadlineTaskCommand extends Command {

    public static final String COMMAND = "deadline";

    private final String taskName;

    private final LocalDate deadline;

    public addDeadlineTaskCommand(String taskName, LocalDate deadline) {
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
