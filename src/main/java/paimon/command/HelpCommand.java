package paimon.command;

import paimon.ChatException;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to display help information to the user. This command triggers
 * the UI handler to show available commands and their descriptions, assisting users in
 * understanding how to interact with the application.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command by invoking the UI handler's method to display
     * help information. This method provides users with guidance on available commands
     * and their usage within the application.
     *
     * @param tasks The task list, not directly used by this command but required by the method signature.
     * @param ui    The UI handler responsible for displaying the help message to the user.
     * @throws ChatException Not thrown by this command but declared due to the method signature.
     */
    @Override
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {
        ui.displayHelpMessage();
    }


    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, as this command is meant to provide information and does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
