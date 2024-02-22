package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Abstract class representing a command to be executed.
 */
public abstract class Command {

    /** Whether the command for the chat to be terminated has been called. */
    private boolean isTerminateChat = false;

    /**
     * Sets the termination status of the chat.
     *
     * @param isTerminateChat A boolean value indicating whether the chat should be terminated.
     */
    public void setIsTerminateChat(boolean isTerminateChat) {
        this.isTerminateChat = isTerminateChat;
    }

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
     * @return String notification if the command is executed successfully.
     */
    public abstract String execute(TaskManager taskManager, Ui userInterface);
}
