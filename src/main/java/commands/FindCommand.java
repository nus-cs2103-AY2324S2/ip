package commands;

import java.util.List;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Task;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public Task execute(TaskList tasks, StorageManager storageManager) {
        List<Task> tasksContainingKeyword = tasks.find(this.keyword);
        if (tasksContainingKeyword.size() == 0) {
            System.out.println("No matching tasks.");
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksContainingKeyword.size(); i++) {
            Task t = tasksContainingKeyword.get(i);
            String str = String.format("%d. %s", i + 1, t);
            System.out.println(str);
        }
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
