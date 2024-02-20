package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays all tasks in the task list.
     */
    public String displayTasks() {
        StringBuilder tasksStr = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            tasksStr.append(j).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return tasksStr.toString();
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
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        List<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.search(keyword)) {
                searchResults.add(task);
            }
        }
        return new TaskList(searchResults);
    }
}
