package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /**
     * Constructs an empty task list.
     */
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        assert tasks != null : "Task list should not be null";
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {

        if (this.tasks == null) {
            this.tasks = new ArrayList<>(); // Initialize the list if it's null
        }
        this.tasks.add(task);
        assert tasks.contains(task) : "Task should be added to the list";
    }

    /**
     * Gets the list of tasks in the task list.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws DukeException If the index is invalid.
     */
    public Task markTaskAsDone(int index) throws DukeException {
        assert index >= 0 && index < tasks.size() : "Index should be within the list range";
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsDone();
            return task;
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Marks a task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws DukeException If the index is invalid.
     */
    public Task unmarkTaskAsDone(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsNotDone();
            return task;
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Deletes a task at the specified index and displays the deletion message using the given UI.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    //    public void deleteTask(int taskIndex, Ui ui) {
    //        if (taskIndex >= 0 && taskIndex < tasks.size()) {
    //            Task removedTask = tasks.remove(taskIndex);
    //            ui.showTaskDeleted(removedTask, tasks.size());
    //        } else {
    //            ui.showInvalidTaskIndex();
    //        }
    //  }
    public Task deleteTask(int taskIndex) throws DukeException {
        assert taskIndex >= 0 && taskIndex < tasks.size() : "taskIndex should be within the valid range";
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            return this.tasks.remove(taskIndex);
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    /**
     * Searches for tasks with descriptions containing the specified keyword.
     *
     * @param keyword The keyword to match in task descriptions.
     * @return List of matching {@link Task} objects, or an empty list if no matches found.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        assert keyword != null : "Keyword should not be null";
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

}
