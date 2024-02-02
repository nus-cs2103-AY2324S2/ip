package zack.util;

import zack.ZackException;
import zack.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("Task index is out of range.");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("Task index is out of range.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
