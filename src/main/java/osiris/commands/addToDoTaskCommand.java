package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the addition of a to-do task.
 */
public class addToDoTaskCommand extends Command {

    public static final String COMMAND = "todo";

    private String taskName;

    /**
     * Constructs an AddToDoTaskCommand object with the specified task name.
     *
     * @param taskName The name of the to-do task.
     */
    public addToDoTaskCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the command to add a to-do task.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return true if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.addToDoTask(this.taskName, false);
        if (isSuccess) {
            userInterface.addToDoTaskSuccessNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
