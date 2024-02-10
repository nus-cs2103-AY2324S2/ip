package eggy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** List of tasks. */
    private List<Task> tasks;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Index of the task to be removed.
     * @return Task that was removed.
     */
    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a list of tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return List of tasks that match the keyword.
     */
    public List<Task> findMatchingTasks(String keyword) {
        return this.tasks.stream().filter(task -> task.getName().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getSize(); i++) {
            sb.append("  ").append(i + 1).append(".").append(this.getTask(i));
            if (i != this.getSize() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of the task list to be saved in a file.
     *
     * @return String representation of the task list to be saved in a file.
     */
    public String toFileString() {
        StringBuilder fileString = new StringBuilder();
        for (Task task : this.tasks) {
            fileString.append(task.toFileString()).append("\n");
        }
        return fileString.toString();
    }
}
