package drew.storage;

import drew.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> ls) {
        this.tasks = ls;
    }

    public ArrayList<Task> getList(){
        return tasks;
    }
}
