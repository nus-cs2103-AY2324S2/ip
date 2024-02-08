package gronk;

import java.util.ArrayList;

/**
 * TaskList class.
 * This contains an ArrayList which stores
 * Task objects.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new Task to the list.
     *
     * @param t The Task to be added to the list.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a Task from the list based on its index.
     *
     * @param index The index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Retrieves a Task from the list based on its index.
     *
     * @param index The index of the Task to be retrieved.
     * @return A Task object.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the size of the TaskList object.
     *
     * @return The number of Task objects in the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the entire ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }
}
