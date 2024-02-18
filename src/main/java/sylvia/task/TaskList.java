package sylvia.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @return true if the task is added, false otherwise.
     */
    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    /**
     * Removes a task from the list. If the task is not in the list, nothing
     * happens.
     *
     * @param task The task to remove.
     * @return The removed task, or null if the task is not in the list.
     */
    public Task removeTask(Task task) {
        if (task == null) {
            return null;
        }

        // Remove the most recent task that is equal to the given task.
        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (tasks.get(i).equals(task)) {
                return tasks.remove(i);
            }
        }
        return task;
    }

    /**
     * Removes a task from the list by index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        return sb.toString().trim();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        tasks.get(index - 1).unmarkAsDone();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}
