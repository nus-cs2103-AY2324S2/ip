package commands;

import java.util.List;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

public class ListCommand extends Command {
    public Task execute(TaskList tasklist, StorageManager storageManager) {
        List<Task> tasks = tasklist.getTasks();
        if (tasks.size() == 0) {
            System.out.println("You currently have no tasks.");
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String str = String.format("%d. %s", i + 1, t);
            System.out.println(str);
        }
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
