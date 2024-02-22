package osiris.commands;

import osiris.exceptions.OsirisException;
import osiris.task.Task;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the "delete" command to remove a task.
 */
public class DeleteTaskCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "delete";

    /** Index to delete. */
    private final int taskIndex;

    /**
     * Constructs a DeleteTaskCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be removed.
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "delete" command to remove a task.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String notification if the command is executed successfully.
     * @throws OsirisException If an error occurs that has not been detected.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        Task deletedTask = taskManager.deleteTask(taskIndex - 1);
        if (deletedTask != null) {
            String deletedTaskDetails = deletedTask.toString();
            int totalTasks = taskManager.getTotalTaskCount();
            return userInterface.displayDeleteTaskNotification(deletedTaskDetails, totalTasks);
        }
        throw new OsirisException("An error has occurred. Please try again.");
    }
}
