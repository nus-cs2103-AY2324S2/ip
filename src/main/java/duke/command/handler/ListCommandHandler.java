package duke.command.handler;

import duke.task.TaskDisplay;
import duke.task.TaskManager;

/**
 * Handles the listing of all tasks.
 *
 * This command handler retrieves and displays all tasks stored in the task manager.
 *
 * Usage: lst
 */
public class ListCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    /**
     * Constructs a ListCommandHandler.
     *
     * @param taskManager the task manager
     * @param taskDisplay the task display
     */
    public ListCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    /**
     * Handles the user's input for listing all tasks.
     *
     * @param userMessage the user's input message
     * @return a message containing the list of all tasks
     */
    @Override
    public String handle(String[] userMessage) {
        return taskDisplay.displayTaskList(taskManager.getTasks());
    }

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    @Override
    public String getDescription() {
        return "Lists all tasks. Usage: lst";
    }
}
