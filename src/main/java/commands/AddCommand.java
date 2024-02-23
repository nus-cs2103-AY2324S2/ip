package commands;

import storage.StorageManager;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class AddCommand extends Command {
    protected Task task;
    protected boolean status = false;

    public AddCommand(String description) {
        this.task = new Todo(description);
    }

    public AddCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    public AddCommand(String description, String startDate, String endDate) {
        this.task = new Event(description, startDate, endDate);
    }

    public Task execute(TaskList tasks, StorageManager storageManager) {
        task.setStatus(status);
        tasks.add(task);
        storageManager.save(tasks);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        return task;
    }

    public boolean isExit() {
        return false;
    }
}
