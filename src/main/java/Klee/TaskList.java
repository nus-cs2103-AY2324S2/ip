package Klee;

import Klee.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList () {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }
}
