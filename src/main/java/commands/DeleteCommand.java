package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

public class DeleteCommand extends Command {
    protected int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public Task execute(TaskList tasks, StorageManager storageManager) {
        Task t = tasks.delete(taskNum);
        storageManager.save(tasks);
        System.out.println("Noted. I've removed this task");
        System.out.println(t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        return t;
    }

    public boolean isExit() {
        return false;
    }
}
