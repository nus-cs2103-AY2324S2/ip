package ghbot.task;

import java.util.List;

/**
 * TaskList Class.
 * Contains a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * TaskList Constructor.
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the list.
     * @param task Adds task to the current list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task from the list.
     * @param index Deletes task from the list using index.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns a specific task.
     * @param index Index of the specific task.
     * @return A specific task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     * @return The size of the task list.
     */
    public int taskSize() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks using List.
     */
    public List<Task> toList() {
        return this.tasks;
    }
}
