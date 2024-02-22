package jerry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a collection of tasks in the chatbot application.
 * Provides methods to manipulate tasks, such as adding, deleting, and listing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        int initialSize = tasks.size();
        Task result = this.tasks.remove(index);
        assert tasks.size() == initialSize - 1 : "Task wasn't removed";
        return result;
    }

    /**
     * Retrieves the size of the tasklist.
     * @return Integer of the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks the task as completed.
     * @param index of the task to mark.
     */
    public void mark(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Unmarks the task as completed.
     * @param index of the task to unmark.
     */
    public void unmark(int index) {
        tasks.get(index).markNotDone();
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds the tasks that corresponds to the date.
     * @param date The date criteria.
     * @return The tasks that meet the criteria.
     */
    public ArrayList<Task> getTasksForDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.isScheduledForDate(date))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
