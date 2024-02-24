package commands;

import storage.StorageManager;
import tasklist.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Executes the ByeCommand, displaying a farewell message.
     *
     * @param tasks          The task list (not used in this command).
     * @param storageManager The storage manager (not used in this command).
     * @return Null, as there is no task to return.
     */
    public String execute(TaskList tasks, StorageManager storageManager) {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether the ByeCommand is an exit command.
     *
     * @return True, as ByeCommand is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
