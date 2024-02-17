package nicky.task;

import java.util.ArrayList;
import java.util.List;

import nicky.NickyException;

/**
 * Represents a list of tasks in the Nicky application.
 * It provides methods for managing tasks such as adding, removing, and listing tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a new TaskList instance with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task object.
     * @throws NickyException If the index is out of range.
     */
    public Task removeTask(int index) throws NickyException {
        if (index < 0 || index >= tasks.size()) {
            throw new NickyException("Task number out of range.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a string containing the list of tasks in the TaskList.
     *
     * @return A string containing the list of tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        } else {
            sortDeadlines();
            String taskWord = tasks.size() == 1 ? "task" : "tasks";
            StringBuilder message = new StringBuilder("Here are the " + taskWord + " in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("\n").append(i + 1).append(". ").append(tasks.get(i));
            }
            return message.toString();
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The Task object to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the string representation of a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The string representation of the task.
     */
    public String get(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Sorts the deadlines in the task list chronologically.
     */
    public void sortDeadlines() {
        tasks.sort((t1, t2) -> {
            if (t1 instanceof Deadline && t2 instanceof Deadline) {
                Deadline d1 = (Deadline) t1;
                Deadline d2 = (Deadline) t2;
                return d1.getBy().compareTo(d2.getBy());
            }

            return (t1 instanceof Deadline) ? -1 : (t2 instanceof Deadline) ? 1 : 0;
        });
    }
}
