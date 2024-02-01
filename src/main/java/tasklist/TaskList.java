package tasklist;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task>  taskList;

    public TaskList() {

        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {

        this.taskList = tasks;
    }

    public ArrayList<Task> getTaskList() {

        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {

        this.taskList = taskList;
    }
}
