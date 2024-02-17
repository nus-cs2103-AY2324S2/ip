package bob;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * A class that encapsulates a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /*
     * A constructor that creates a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * This method returns the size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /*
     * This method adds a task into the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /*
     * This method removes a task from the list.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /*
     * This method retrieves the task from the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /*
     * This method clears all tasks from the list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /*
     * An Iterator method that goes through each task in the list.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
