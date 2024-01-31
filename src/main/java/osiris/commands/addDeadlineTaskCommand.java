package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

import java.time.LocalDate;

/**
 * Command class representing the addition of a deadline task.
 */
public class addDeadlineTaskCommand extends Command {

    public static final String COMMAND = "deadline";

    private final String taskName;

    private final LocalDate deadline;

    /**
     * Constructs an AddDeadlineTaskCommand object with the specified task name and deadline.
     *
     * @param taskName The name of the deadline task.
     * @param deadline The deadline of the task.
     */
    public addDeadlineTaskCommand(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return true if the command is executed successfully, false otherwise.
     */
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
