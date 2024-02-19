package yue.Tasks;

import java.util.List;


/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        tasks.remove(index);

    }


    /**
     * Gets all tasks in the task list.
     *
     * @return The list of tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }


    /**
     * Gets the task at the specified index in the task list.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return tasks.get(index);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }
}
