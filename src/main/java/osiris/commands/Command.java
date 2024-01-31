package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Abstract class representing a command to be executed.
 */
public abstract class Command {

    private boolean isTerminateChat = false;

    /**
     * Checks if the command is to terminate the chat.
     *
     * @return true if the command is to terminate the chat, false otherwise.
     */
    public boolean isTerminateChat() {
        return isTerminateChat;
    }

    /**
     * Executes the command.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return true if the command is executed successfully, false otherwise.
     */
    public abstract boolean execute(TaskManager taskManager, Ui userInterface);
}
