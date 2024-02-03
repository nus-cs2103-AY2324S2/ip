package duke.task;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks to be stored.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Returns a list of tasks containing the tasks provided.
     *
     * @param tasks The tasks to store in the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an empty list of tasks.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Delets a task from the list of tasks.
     *
     * @param taskNumber The index of the task to delete.
     * @return The task deleted.
     * @throws DukeException If the index provided is greater than the list of tasks.
     */
    public String delete(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }
        Task t = tasks.remove(taskNumber - 1);
        return t.toString();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskNumber The index of the task to mark as done.
     * @return The task marked as done.
     * @throws DukeException If the index provided is greater than the list of tasks.
     */
    public String mark(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }
        Task t = tasks.get(taskNumber - 1);
        t.mark();
        return t.toString();
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param taskNumber The index of the task to mark as not done.
     * @return The task marked as not done.
     * @throws DukeException If the index provided is greater than the list of tasks.
     */
    public String unmark(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.get(taskNumber - 1);
        t.unmark();
        return t.toString();
    }

    /**
     * Returns the list of tasks in an ArrayList.
     *
     * @return The list of tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }
}
