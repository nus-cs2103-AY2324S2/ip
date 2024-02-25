package roland;
import java.util.ArrayList;

import task.Task;



/**
 * The TaskList class represents a collection of tasks in the Roland task management application.
 * It provides methods for managing tasks, such as adding, removing, and retrieving tasks from the list.
 *
 * @author wolffe88
 */

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the specified ArrayList of tasks.
     *
     * @param tasks The ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new TaskList with an empty ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the ArrayList of tasks stored in the TaskList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Removes the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }



}
