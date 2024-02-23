package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;
import exceptions.CalException;

public abstract class Command {
    public abstract Task execute(TaskList tasks, StorageManager storageManager) throws CalException;
    public abstract boolean isExit();
}
