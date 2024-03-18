package anna;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> storedTasks;

    /**
     * Constructs a new TaskList with the specified list of stored tasks.
     *
     * @param storedTasks the list of stored tasks
     */
    TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    /**
     * Constructs a new empty TaskList.
     */
    TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of stored tasks.
     *
     * @return the list of stored tasks
     */
    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }

    /**
     * Checks if the given index is valid for accessing a task in the list.
     *
     * @param idx the index to check
     * @return true if the index is valid, otherwise false
     */
    public boolean checkTaskIdx(int idx) {
        return 0 <= idx && idx < storedTasks.size();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int numberOfTask() {
        return storedTasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param t the task to add
     * @throws AnnaException if an exception occurs while adding the task
     */
    public void addTask(Task t) throws AnnaException {
        storedTasks.add(t);
    }

    /**
     * Sets the done status of the task at the specified index.
     *
     * @param idx the index of the task to set the done status for
     * @param done the status indicating whether the task is done or not
     * @throws AnnaException if an exception occurs while setting the done status
     */
    public void setDone(int idx, boolean done) throws AnnaException {
        storedTasks.get(idx).setDone(done);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param idx the index of the task to remove
     * @return the removed task
     * @throws AnnaException if an exception occurs while removing the task
     */
    public Task popTask(int idx) throws AnnaException {
        Task t = storedTasks.get(idx);
        storedTasks.remove(idx);
        return t;
    }

    /**
     * Retrieves the task at the specified index without removing it from the list.
     *
     * @param idx the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task peekTask(int idx) {
        return storedTasks.get(idx);
    }
}
