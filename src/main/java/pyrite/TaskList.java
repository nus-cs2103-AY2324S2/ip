package pyrite;

import java.io.Serializable;
import java.util.ArrayList;

import pyrite.task.Task;

/**
 * Stores a list of tasks.
 */
public class TaskList implements Serializable {

    /**
     * Returns index of a given task.
     *
     * @param task Task to find index of.
     * @return Index of task.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    public int indexOf(Task task) {
        return this.tasks.indexOf(task);
    }

    /**
     * Returns the size of list.
     *
     * @return Size of list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns string representation of the whole list.
     *
     * @return String representation of the whole list.
     */
    public String toString() {
        String output = "";
        for (Task t : this.tasks) {
            output += (this.tasks.indexOf(t) + 1) + ". " + t.toString();
            if (this.tasks.indexOf(t) == this.tasks.size() - 1) {
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
        return this.tasks.get(id).toString();
    }

    /**
     * Returns string representation of a filtered task list.
     *
     * @param keyword Keyword to filter by.
     * @return String representation of filtered task list.
     */
    public String filteredString(String keyword) {
        String output = "";
        for (Task t : this.tasks) {
            if (t.toString().contains(keyword)) {
                output += (this.tasks.indexOf(t) + 1) + ". " + t.toString();
                if (this.tasks.indexOf(t) == this.tasks.size() - 1) {
                    break;
                }
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Sets the status of a task.
     *
     * @param id Index of task.
     * @param status Status to set task to.
     */
    public void setStatus(int id, Task.Status status) {
        this.tasks.get(id).setStatus(status);
    }

    /**
     * Removes a task from the list.
     *
     * @param id Index of task to remove.
     */
    public void remove(int id) {
        this.tasks.remove(id);
    }

    /**
     * Adds task to list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns whether the given id is valid.
     *
     * @param id ID to check.
     * @return Whether the given id is valid.
     */
    public boolean isValidId(int id) {
        if (id < 0 || id >= this.tasks.size()) {
            return false;
        }
        return true;
    }
}
