package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();

    public TaskList() {

    }
    public Task getTask(int index) {
        return this.list.get(index);
    }
    public int getSize() {
        return this.list.size();
    }
    public void addTask(Task task) {
        this.list.add(task);
    }
    public void printTaskList() {
        int size = this.list.size();
        System.out.println("Here is  your list so far! \n----->");
        for (int i = 0; i < size; i++) {
            System.out.println(this.list.get(i).getTask());
        }
        System.out.println("----->");
    }
}
