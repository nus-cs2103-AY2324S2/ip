package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskArray) {
        this.taskList = taskArray;
    }

    public int length() {
        return this.taskList.size();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
    }

    public Task markTask(int taskIndex, boolean isMarked) {
        Task taskToMark = this.taskList.get(taskIndex);
        taskToMark.setDone(isMarked);
        return taskToMark;
    }

    public Task deleteTask(int taskIndex) {
        return this.taskList.remove(taskIndex);
    }
}
