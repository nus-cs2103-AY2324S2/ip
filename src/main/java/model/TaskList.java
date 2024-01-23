package model;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(Task task) {
        this.taskList.remove(task);
    }

    public int size() {
        return this.taskList.size();
    }
}
