package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the "bye" command to terminate the chat.
 */
public class terminateChatCommand extends Command {

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
     * @return Always returns true.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        taskManager.termintate();
        return true;
    }
}
