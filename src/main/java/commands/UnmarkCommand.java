package commands;

import exceptions.CalException;
import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

public class UnmarkCommand extends Command {
    protected int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public Task execute(TaskList tasks, StorageManager storageManager) throws CalException {
        Task t = tasks.unmark(taskNum);
        storageManager.save(tasks);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        return t;
    }

    public boolean isExit() {
        return false;
    }
}
