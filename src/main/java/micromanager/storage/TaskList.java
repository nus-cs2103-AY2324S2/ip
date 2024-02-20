package micromanager.storage;

import java.util.ArrayList;
import java.util.List;

import micromanager.exceptions.DukeException;
import micromanager.tasks.Task;

/**
 * TaskList class represents a list of tasks.
 * It provides methods for adding, removing, and accessing tasks in the list.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param taskList The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves the task at the specified index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws DukeException If the index is invalid.
     */
    public Task get(int index) throws DukeException {
        boolean isOutOfBounds = index < 0 || index > taskList.size() - 1;
        if (isOutOfBounds) {
            throw new DukeException("OOPS!!! Invalid index provided.");
        }
        return this.taskList.get(index);
    }

    /**
     * Removes and returns the task at the specified index in the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) throws DukeException {
        boolean isOutOfBounds = index < 0 || index > taskList.size() - 1;
        if (isOutOfBounds) {
            throw new DukeException("OOPS!!! Invalid index provided.");
        }
        Task target = this.taskList.get(index);
        this.taskList.remove(index);

        return target;
    }

    /**
     * Updates the task at the specified index in the task list with the provided new task.
     *
     * @param index   The index of the task to update.
     * @param newTask The new task to replace the existing one.
     * @throws DukeException If the index is invalid.
     */
    public void update(int index, Task newTask) throws DukeException {
        boolean isOutOfBounds = index < 0 || index > taskList.size() - 1;
        if (isOutOfBounds) {
            throw new DukeException("OOPS!!! Invalid index provided.");
        }
        this.taskList.set(index, newTask);
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            taskListString.append(i + 1).append(".").append(taskList.get(i));

            if (i < taskList.size() - 1) {
                taskListString.append("\n");
            }
        }

        return taskListString.toString();
    }
}
