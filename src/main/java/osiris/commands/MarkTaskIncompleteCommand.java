package osiris.commands;

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
     * @return true if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.markTaskIncomplete(taskIndex - 1);
        if (isSuccess) {
            userInterface.displayMarkTaskIncompleteNotification(taskManager.getTask(taskIndex - 1).toString());
        }
        return true;
    }
}
