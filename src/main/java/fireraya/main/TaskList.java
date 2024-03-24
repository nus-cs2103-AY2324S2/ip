package fireraya.main;

import fireraya.exception.FirerayaException;
import fireraya.task.Task;

import java.util.ArrayList;

/**
 * A class for handling the data held in the program.
 *
 * An Arraylist of tasks and its associated information will be handled here.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for an empty tasklist.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor for a tasklist.
     *
     * @param tasks An ArrayList of tasks.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the entire ArrayList of tasks.
     *
     * @return an ArrayList of tasks in the current TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return integer number representing the size of the ArrayList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a specified Task as done.
     *
     * @param i Index of the task to be marked.
     */
    public void markAsDone(int i) {
        tasks.get(i).markAsDone();
    }

    /**
     * Marks a specified Task as undone.
     *
     * @param i Index of the task to be unmarked.
     */
    public void unmark(int i) {
        tasks.get(i).markAsUndone();
    }

    /**
     * Deletes a specified Task from the TaskList.
     *
     * @param i Index of the task to be deleted.
     */
    public void delete(int i) {
        tasks.remove(i);
    }

    /**
     * Filters out the TaskList to only include tasks which have a certain keyword.
     *
     * @param key String keyword to search for in the TaskList.
     * @return A new ArrayList with only the filtered tasks.
     */
    public ArrayList<Task> filter(String key) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTask().contains(key)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

}
