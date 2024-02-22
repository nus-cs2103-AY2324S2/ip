package lilybot;

import java.util.ArrayList;

/**
 * TaskList class for handling task list.
 */
public class TaskList {

    /** An arraylist of task for TaskList */
    private ArrayList<Task> ls;

    /**
     * Construct a TaskList object.
     *
     * @param ls The arraylist of task to be converted to taskList.
     */
    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public ArrayList<Task> getLs() {
        return ls;
    }

    public Task get(int i) {
        Task t = getLs().get(i);
        return t;
    }

    public int getSize() {
        return getLs().size();
    }

    /**
     * Adds a task to taskList.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        getLs().add(t);
    }

    /**
     * Adds a task to taskList at a specific index.
     *
     * @param index The index of the task in list.
     * @param t The task to be added.
     */
    public void add(int index, Task t) {
        getLs().add(index, t);
    }

    /**
     * Removes a task from taskList according to the task number entered.
     *
     * @param taskNumtoRemove The task number of the to-be-deleted task.
     */
    public void remove(int taskNumtoRemove) {
        getLs().remove(taskNumtoRemove);
    }

}
