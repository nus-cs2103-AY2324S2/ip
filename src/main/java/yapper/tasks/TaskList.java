package yapper.tasks;

import java.util.ArrayList;
import java.util.List;
/**
 * A custom extension of ArrayList to represent a list of tasks.
 */
public class TaskList extends ArrayList<Task> {

    public TaskList() {}

    /**
     * Constructs a TaskList containing the elements of the specified collection.
     *
     * @param tasks The collection whose elements are to be placed into this TaskList.
     */

    public TaskList(List<Task> tasks) {

        super(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @throws AssertionError If the task to add is null.
     */
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        this.add(task);
    }

    public List<Task> getTasks() {
        return this;
    }
}
