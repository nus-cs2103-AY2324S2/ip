package commands;

import java.util.List;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

public class FindCommand {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public Task execute(TaskList tasks, StorageManager storageManager) {
        List<Task> tasksContainingKeyword = tasks.find(this.keyword);
        System.out.println("Here are the matching tasks in your list:");
        if (tasksContainingKeyword.size() == 0) {
            System.out.println("No matching tasks.");
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasksContainingKeyword.size(); i++) {
            Task t = tasks.getTask(i);
            String str = String.format("%d. %s", i + 1, t);
            System.out.println(str);
        }
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        return task;
    }

    public boolean isExit() {
        return false;
    }
}
