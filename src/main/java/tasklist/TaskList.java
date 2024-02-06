package tasklist;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task>  tasks;

    public TaskList() {

        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {

        return tasks;
    }
}
