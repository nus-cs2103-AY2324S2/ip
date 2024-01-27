package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    protected ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getTaskList() {
        return taskArrayList;
    }

    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    public Task removeTask(int index) {
        return taskArrayList.remove(index);
    }

    public int getListLength() {
        return this.taskArrayList.size();
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }
}
