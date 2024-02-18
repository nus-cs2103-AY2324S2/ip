package task;

import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;

    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i - 1);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int indexOfTask) {
        Task task = tasks.remove(indexOfTask - 1);
        return task;
    }

    public LinkedList<Task> getList() {
        return tasks;
    }
}
