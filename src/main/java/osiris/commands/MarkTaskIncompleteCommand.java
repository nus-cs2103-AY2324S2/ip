package osiris.commands;

import osiris.exceptions.OsirisException;
import osiris.task.TaskManager;
import osiris.ui.Ui;


/**
 * Command class to mark a task as incomplete.
 */
public class MarkTaskIncompleteCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "unmark";

    /** Index to mark as incomplete. */
    private final int taskIndex;


    /**
     * Constructs a MarkTaskIncompleteCommand object with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as incomplete.
     */
    public MarkTaskIncompleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark task incomplete command.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String notification if the command is executed successfully.
     * @throws OsirisException If an error occurs that has not been detected.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.markTaskIncomplete(taskIndex - 1);
        if (isSuccess) {
            String taskDetails = taskManager.getTask(taskIndex - 1).toString();
            return userInterface.displayMarkTaskIncompleteNotification(taskDetails);
        }
        throw new OsirisException("An error has occurred. Please try again.");
    }
}
