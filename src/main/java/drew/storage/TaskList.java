package drew.storage;

import drew.task.Task;

import java.util.ArrayList;

/**
 * This class represents the task list structure.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor for creating an empty task list;
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for creating a task list from an existing list.
     * @param ls
     */
    public TaskList(ArrayList<Task> ls) {
        this.tasks = ls;
    }

    public ArrayList<Task> getList(){
        return tasks;
    }
}
