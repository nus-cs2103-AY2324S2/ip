package gpt;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the TaskList contains a specified task.
     *
     * @param task The task to be checked.
     * @return True if the TaskList contains the task, false otherwise.
     */
    public Boolean containsTask(Task task) {
        return tasks.contains(task);
    }

    /**
     * Finds tasks that contain a specified keyword.
     *
     * @param keyword The keyword to be searched.
     * @return A TaskList containing tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        assert keyword != null : "Keyword should not be null"; // Check that the keyword is not null
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Get the number of tasks completed in the past week.
     *
     * @return The number of tasks completed in the past week.
     */
    public int getCompletedTasksInPastWeek() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusWeeks(1);

        ArrayList<Task> completedTasks = getCompletedTasks();
        int completedTasksInPastWeek = 0;

        for (Task task : completedTasks) {
            LocalDateTime completionDate = task.getCompletedDate();
            if (completionDate != null && completionDate.isAfter(oneWeekAgo) && completionDate.isBefore(now)) {
                completedTasksInPastWeek++;
            }
        }

        return completedTasksInPastWeek;
    }

    /**
     * Get the list of completed tasks.
     *
     * @return List of completed tasks.
     */
    private ArrayList<Task> getCompletedTasks() {
        ArrayList<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }
}

