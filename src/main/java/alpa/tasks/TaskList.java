package alpa.tasks;

import alpa.exceptions.AlpaException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds all the tasks in the given list to the task list.
     *
     * @param tasks the list of tasks to be added
     */
    public void addAll(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws AlpaException If the index is invalid.
     */
    public Task deleteTask(int index) throws AlpaException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        } else {
            throw new AlpaException("\nInvalid task number, human!!");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that has been marked as done.
     * @throws AlpaException If the index is out of bounds.
     */
    public Task markTaskAsDone(int index) throws AlpaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AlpaException("\nTask does not exist, human!!");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to mark as not done.
     * @return The task that was marked as not done.
     * @throws AlpaException If the index is out of bounds.
     */
    public Task markTaskAsNotDone(int index) throws AlpaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AlpaException("\nTask does not exist, human!!");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return a copy of the list of tasks
     */
    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
