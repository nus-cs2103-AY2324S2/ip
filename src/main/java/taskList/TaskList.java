package taskList;

import java.io.Serializable;
import java.util.ArrayList;

import taskList.tasks.Task;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> thelist) {
        this.taskList = thelist;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int taskNo) {
        try {
            Task removedTask = taskList.remove(taskNo-1);
            return removedTask;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
        } 
        return null;
    }

    public void markTask(int taskNo) {
        try {
            taskList.get(taskNo-1).markItem();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
        } 
    }

    public void unmarkTask(int taskNo) {
        try {
            taskList.get(taskNo-1).unmarkItem();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
        } 
    }

    public int size() {
        return taskList.size();
    }

    public Task getTask(int i) {
        try {
            return taskList.get(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
        } 
        return null;
    }
    
}
