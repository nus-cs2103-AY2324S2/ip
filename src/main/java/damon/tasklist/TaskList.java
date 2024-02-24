package damon.tasklist;

import damon.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;


    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void markTask(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void unMarkTask(int index) {
        this.taskList.get(index).markBackNotDone();
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }
}
