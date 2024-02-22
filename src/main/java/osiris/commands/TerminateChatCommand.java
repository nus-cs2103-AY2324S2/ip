package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the "bye" command to terminate the chat.
 */
public class TerminateChatCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "bye";

    /**
     * Checks if the chat should be terminated.
     *
     * @return Always returns true to indicate termination.
     */
    public boolean isTerminateChat() {
        return true;
    }

    /**
     * Executes the "bye" command to terminate the chat.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String saying goodbye.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        taskManager.terminate();
        return userInterface.displayGoodbyes();
    }
}
