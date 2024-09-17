package guanguan;

import java.util.ArrayList;

/**
 * Represents a list of tasks and its helper functions.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList with existing tasks.
     *
     * @param tasks list of tasks
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a task from TaskList.
     *
     * @param index index of the task in the TaskList
     * @return task at the specified index
     * @throws GgException if index is out of bounds
     */
    public Task get(int index) throws GgException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GgException("Invalid task ID");
        }
    }

    /**
     * Gets all tasks from TaskList.
     *
     * @return list of tasks
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Removes a task from TaskList.
     * Wrapper function for ArrayList.remove().
     *
     * @param index index of the task in the TaskList
     * @return task at the specified index
     * @throws GgException if index is out of bounds
     */
    public Task remove(int index) throws GgException {
        try {
            return this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new GgException("Invalid task ID");
        }
    }

    /**
     * Finds tasks that contain keyword.
     *
     * @param keyword keyword to be searched
     * @return list of tasks containing the keyword
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : getAllTasks()) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Gets the size of TaskList.
     * Wrapper function for ArrayList.size().
     *
     * @return size of TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if TaskList is empty.
     * Wrapper function for ArrayList.isEmpty().
     *
     * @return true if TaskList is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

}
