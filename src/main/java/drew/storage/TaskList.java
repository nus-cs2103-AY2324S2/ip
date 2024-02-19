package drew.storage;

import java.util.ArrayList;

import drew.task.Task;

/**
 * This class represents the task list structure.
 */
public class TaskList {
    private ArrayList<Task> tasks;

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

    public ArrayList<Task> getList() {
        return tasks;
    }
}
