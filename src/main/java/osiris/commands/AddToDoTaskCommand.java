package osiris.commands;

import osiris.exceptions.OsirisException;
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
     * @return String notification if the command is executed successfully.
     * @throws OsirisException If an error occurs that has not been detected.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.addToDoTask(taskName, false);
        if (isSuccess) {
            return userInterface.displayToDoTaskAdditionNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        throw new OsirisException("An error has occurred. Please try again.");
    }
}
