package osiris.commands;

import osiris.exceptions.OsirisException;
import osiris.task.Task;
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
        Task newTask = taskManager.addToDoTask(taskName, false);
        String taskDetails = newTask.toString();
        int totalTasks = taskManager.getTotalTaskCount();
        return userInterface.displayToDoTaskAdditionNotification(taskDetails, totalTasks);
    }
}
