package commands;

import exceptions.CalException;
import tasks.Task;
import storage.StorageManager;
import tasklist.TaskList;

public class MarkCommand extends Command {
    protected int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public Task execute(TaskList tasks, StorageManager storageManager) throws CalException {
        Task t = tasks.mark(taskNum);
        storageManager.save(tasks);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        return t;
    }

    public boolean isExit() {
        return false;
    }
}
