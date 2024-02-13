package capone;

import java.util.ArrayList;
import java.util.Iterator;

import capone.tasks.Task;

/**
 * The TaskList class represents a list of tasks.
 * It provides methods to manage and iterate through tasks.
 *
 * @author Tay Rui-Jie
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskList The list of tasks to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets the task at the specified index in the TaskList.
     *
     * @param ndx The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int ndx) {
        return this.taskList.get(ndx);
    }

    /**
     * Gets the last task in the TaskList.
     *
     * @return The last task in the TaskList.
     */
    public Task getLastTask() {
        return this.taskList.get(this.getSize() - 1);
    }

    /**
     * Removes and returns the task at the specified index in the TaskList.
     *
     * @param ndx The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int ndx) {
        return this.taskList.remove(ndx);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @return true if the task is added successfully, false otherwise.
     */
    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    /**
     * Returns an iterator over the tasks in the TaskList.
     *
     * @return An iterator over the tasks in the TaskList.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }
}
