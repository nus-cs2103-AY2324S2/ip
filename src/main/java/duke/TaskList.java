package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> getItems() {
        return tasks;
    }

    public Task getItem(int index) {
        return tasks.get(index);
    }

    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkDone(int index) {
        tasks.get(index).unmarkDone();
    }

    public void delete(int index) {
        tasks.remove(index);
    }
}
