package paimon.command;


import paimon.ChatException;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to exit the application. This command does not perform any action
 * on the task list but signals the application to terminate its execution.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command. This method is intentionally left blank as the command
     * does not perform any actions on the task list or UI, but merely indicates the application
     * should exit.
     *
     * @param tasks The task list, not used by this command.
     * @return A String to be displayed.
     * @throws ChatException Not thrown by this command.
     */
    @Override
    public String execute(TaskList tasks) throws ChatException {
        return "";
    }

    /**
     * Indicates that executing this command should signal the application to exit.
     *
     * @return true always, as this command signals the application to terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
