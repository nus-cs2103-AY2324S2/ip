package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

public class ByeCommand extends Command {
    public Task execute(TaskList tasks, StorageManager storageManager) {
        System.out.println("Bye. Hope to see you again soon!");
        return null;
    }

    public boolean isExit() {
        return true;
    }
}
