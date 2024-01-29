/*
 * TaskList.java
 * This class represents a list of tasks in the Duke application.
 * It provides methods for managing tasks such as adding, removing, and listing tasks.
 */

package duke.task;

import duke.DukeException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a new TaskList instance with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task object.
     * @throws DukeException If the index is out of range.
     */
    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("duke.task.Task number out of range.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Lists the tasks in the TaskList and prints them to the provided PrintWriter.
     *
     * @param writer The PrintWriter to write the task list to.
     */
    public void listTasks(PrintWriter writer) {
        if (tasks.isEmpty()) {
            writer.println("Your task list is empty.");
        } else {
            String taskWord = tasks.size() == 1 ? "task" : "tasks";
            writer.println("Here are the " + taskWord + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                writer.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The Task object to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the string representation of a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The string representation of the task.
     */
    public String get(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
