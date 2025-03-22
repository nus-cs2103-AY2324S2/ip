package objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TaskList is a class representing a list of tasks.
 * It extends ArrayList and implements the Serializable interface for object serialization.
 */
public class TaskList extends ArrayList<Task> implements Serializable {

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.add(task);
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        Task task = this.get(index);

        if (task != null) {
            task.mark();
        }
    }

    /**
     * Unmarks a task at the specified index.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        Task task = this.get(index);

        if (task != null) {
            task.unmark();
        }
    }
}
