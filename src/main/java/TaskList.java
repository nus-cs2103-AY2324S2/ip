package main.java;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<Task>();

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

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public String toString() {
        int size = this.list.size();
        String returnString = "";
        if (size == 0) {
            return returnString;
        }
        for (int i = 0; i < size; i++) {
            String current = String.valueOf(i + 1);
            if (i == size - 1) {
                returnString = returnString + current + ": " + this.list.get(i).getTask();
            } else {
                returnString = returnString + current + ": " + this.list.get(i).getTask() + "\n";
            }
        }
        return returnString;
    }
}
