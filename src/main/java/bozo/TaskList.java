package bozo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {

    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the specified tasks.
     *
     * @param tasks The tasks to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
        return list.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }

}
