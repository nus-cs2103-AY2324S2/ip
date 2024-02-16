package duke.task;

import java.util.ArrayList;

/**
 * The class representing a list of tasks.
 * */
public class TaskList {

    /* ArrayList containing all the current tasks. */
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into the tasklist.
     *
     * @param task The task to be added into the list.
     * */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param task The task to be removed from the list.
     * */
    public void remove(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Returns the size of the tasklist.
     *
     * @return The size of the tasklist.
     * */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a specific task (by index) from the tasklist.
     *
     * @param index The index of the task to be fetched.
     * @return The task to be fetched.
     * */
    public Task get(int index) {
        return this.taskList.get(index);
    }
}
