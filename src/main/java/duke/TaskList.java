package duke;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * It encapsulates an ArrayList of Task objects and
 * provides methods to manipulate the list.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The Task object at the specified index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the ArrayList containing all the tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Finds all tasks in the task list that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return An ArrayList of Task objects that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
