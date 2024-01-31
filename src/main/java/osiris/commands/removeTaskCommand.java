package osiris.commands;

import osiris.task.Task;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the "delete" command to remove a task.
 */
public class removeTaskCommand extends Command {


    public static final String COMMAND = "delete";

    private int taskIndex;

    /**
     * Constructs a RemoveTaskCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be removed.
     */
    public removeTaskCommand(int taskIndex) {
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
        Task removedTask = taskManager.removeTask(this.taskIndex - 1);
        if (removedTask != null) {
            userInterface.removeTaskSuccessNotification(removedTask.toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
