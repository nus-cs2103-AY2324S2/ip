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
        try {
            return this.taskList.remove(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("there isn't an available task to delete.");
        }

    }

    public Task getTask(int idx) {
        try {
            return this.taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("this task is not available/ does not exist.");
        }
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