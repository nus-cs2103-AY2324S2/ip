package duke.tasklist;

import duke.task.Task;
import duke.exception.DukeException;


import java.util.ArrayList;

/**
 * Represents the list of tasks in the Duke application. It encapsulates an ArrayList of Task
 * objects and provides methods to manipulate this list, such as adding or deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with a pre-defined list of tasks.
     * @param tasks An ArrayList of Task objects to be included in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on the given index.
     * @param index The index of the task in the TaskList to be deleted.
     * @return The Task object that was deleted.
     * @throws DukeException If the index is out of bounds (i.e., if no task exists at the given index).
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(" OOPS! Your Only Friend could not find that index.");
        }
        Task deletedTask = tasks.get(index);
        tasks.remove(index);

        return deletedTask;
    }

    /**
     * Retrieves the list of tasks.
     * @return An ArrayList containing the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     * @return The size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

}
