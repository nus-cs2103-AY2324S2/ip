package osiris.commands;

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
     * @return Always returns true.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        Task deletedTask = taskManager.deleteTask(taskIndex - 1);
        if (deletedTask != null) {
            userInterface.displayDeleteTaskNotification(deletedTask.toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
