package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

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
    public Task execute(TaskList tasks, StorageManager storageManager) {
        System.out.println("Bye. Hope to see you again soon!");
        return null;
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
