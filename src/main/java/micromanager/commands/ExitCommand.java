package micromanager.commands;

import micromanager.storage.Storage;
import micromanager.storage.TaskList;

/**
 * ExitCommand class represents a command to exit the application.
 * It extends the Command class and provides methods to execute the command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param storage  The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true since this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
