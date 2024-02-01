package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    // new tasklist
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    // existing tasklist
    public TaskList(TaskList existing) {
        this.taskList = existing.taskList;
    }

    // duke.TaskList actions
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task rmvTask(int idx) {
        return this.taskList.remove(idx);
    }

    public Task getTask(int idx) {
        return this.taskList.get(idx);
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void printTasks() {
        System.out.println("    Here are the items in your list: ");
        for (int i = 0; i < this.taskList.size(); i++) {
            String listIdx = i + 1 + ". ";
            Task currTask = this.taskList.get(i);
            System.out.println("    " + listIdx + currTask);
        }
    }

}