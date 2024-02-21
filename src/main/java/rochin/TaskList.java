package rochin;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Construct an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task to the TaskList
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Delete a task from the TaskList based on its index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.remove(taskIndex - 1);
        }
    }

    /**
     * Mark a task as done based on its index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
        }
    }

    /**
     * Unmark a task as done based on its index.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public void unmarkTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsNotDone();
        }
    }

    /**
     * Retrieve a copy of all tasks in the TaskList.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    private boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= tasks.size();
    }

    /**
     * Load tasks from a list of strings.
     *
     * @param lines The list of strings representing tasks.
     * @throws RochinException if there's an error during loading.
     */
    public void load(List<String> lines) throws RochinException {
        for (String line : lines) {
            Task task = new Task(line);
            task.createTaskFromFileString(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    /**
     * Convert all tasks to a list of strings.
     *
     * @return A list of strings representing tasks.
     */
    public List<String> convertTasksToStrings() {
        List<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(task.toFileString());
        }
        return taskStrings;
    }

    /**
     * Search for tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the given keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
