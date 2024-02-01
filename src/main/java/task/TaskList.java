package task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public int size() {
        return this.taskList.size();
    }
}
