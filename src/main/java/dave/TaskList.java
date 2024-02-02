package dave;

import java.util.ArrayList;

import dave.tasks.*;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void deleteTask(int taskNumber) {
        this.taskList.remove(taskNumber);
    }

    public void addTask(Todo newTask) {
        this.taskList.add(newTask);
    }

    public void addTask(Deadline newTask) {
        this.taskList.add(newTask);
    }

    public void addTask(Event newTask) {
        this.taskList.add(newTask);
    }
}
