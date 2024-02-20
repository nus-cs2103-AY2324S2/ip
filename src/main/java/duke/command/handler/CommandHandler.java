package duke.command.handler;

import duke.task.TaskManager;

/**
 * A base class for handling commands.
 *
 * This abstract class provides basic functionality for handling commands.
 */
public abstract class CommandHandler {
    protected final TaskManager taskManager;

    /**
     * Constructs a CommandHandler.
     *
     * @param taskManager the task manager
     */
    public CommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Checks if a string represents a numeric value.
     *
     * @param str the string to check
     * @return true if the string represents a numeric value, false otherwise
     */
    protected boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Handles the user's input for a command.
     *
     * @param userMessage the user's input message
     * @return a message indicating the result of handling the command
     */
    public abstract String handle(String[] userMessage);

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    public abstract String getDescription();
}
