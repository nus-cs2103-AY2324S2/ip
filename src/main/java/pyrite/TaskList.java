package pyrite;

import pyrite.task.Task;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores a list of tasks.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns index of a given task.
     *
     * @param task Task to find index of.
     * @return Index of task.
     */
    public int indexOf(Task task) {
        return this.list.indexOf(task);
    }

    /**
     * Returns the size of list.
     *
     * @return Size of list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns string representation of the whole list.
     *
     * @return String representation of the whole list.
     */
    public String toString(){
        String output = "";
        for (Task t : this.list) {
            output += (this.list.indexOf(t) + 1) + ". " + t.toString();
            if (this.list.indexOf(t) == this.list.size() - 1) {
                break;
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Returns string representation of a task.
     *
     * @param id Index of task.
     * @return String representation of task.
     */
    public String toString(int id) {
        return this.list.get(id).toString();
    }

    /**
     * Sets the status of a task.
     *
     * @param id Index of task.
     * @param status Status to set task to.
     */
    public void setStatus(int id, Task.Status status) {
        this.list.get(id).setStatus(status);
    }

    /**
     * Removes a task from the list.
     *
     * @param id Index of task to remove.
     */
    public void remove(int id) {
        this.list.remove(id);
    }

    /**
     * Adds task to list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Returns whether the given id is valid.
     *
     * @param id ID to check.
     * @return Whether the given id is valid.
     */
    public boolean isValidId(int id) {
        if (id < 0 || id >= this.list.size()) {
            return false;
        }
        return true;
    }
}
