package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class to mark a task as completed.
 */
public class markTaskCompletedCommand extends Command {

    public static final String COMMAND = "mark";

    private final int taskIndex;

    /**
     * Constructs a MarkTaskCompletedCommand object with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as completed.
     */
    public markTaskCompletedCommand(int taskIndex) {

        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark task completed command.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return true if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.markTaskCompleted(this.taskIndex - 1);
        if (isSuccess) {
            userInterface.markTaskCompletedSuccessNotification(taskManager.getTask(this.taskIndex - 1).toString());
        }
        return true;
    }
}
