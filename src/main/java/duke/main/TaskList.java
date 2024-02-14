package duke.main;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks to create the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task get(int zeroItem) {
        return tasks.get(zeroItem);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done based on the given zero-indexed task number.
     *
     * @param num The zero-indexed task index to mark as done.
     */
    public void markAsDone(int num) {
        tasks.get(num).markAsDone();
    }

    /**
     * Marks a task as not done based on the given zero-indexed task number.
     *
     * @param num The zero-indexed task index to mark as not done.
     */
     public void unMarkAsDone(int num) {
        tasks.get(num).unMarkAsDone();
    }

    /**
     * Deletes a task from the TaskList based on the given zero-indexed task number.
     *
     * @param item The zero-indexed task number to be deleted.
     * @throws DukeException If asked to delete a task with one-indexed task number
     * of 0 or if one-indexed task number is greater than the size of the task list.
     */
     public void deleteTask(int item) throws DukeException {
        boolean isWithinLowerLimit = item >= 0;
        boolean isWithinUpperLimit = item < tasks.size();
        if (isWithinLowerLimit && isWithinUpperLimit && tasks.get(item) != null) {
            tasks.remove(item);
        } else {
            int oneItem = item + 1;
            throw new DukeException("Error! Task number '" + oneItem + "' does not exist.");
        }
    }
}