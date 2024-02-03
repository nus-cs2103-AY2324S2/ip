package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the addition of a to-do task.
 */
public class AddToDoTaskCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "todo";

    /** Name of the Task. */
    private final String taskName;

    /**
     * Constructs an AddToDoTaskCommand object with the specified task name.
     *
     * @param taskName The name of the to-do task.
     */
    public AddToDoTaskCommand(String taskName) {
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
        boolean isSuccess = taskManager.addToDoTask(taskName, false);
        if (isSuccess) {
            userInterface.displayToDoTaskAdditionNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
