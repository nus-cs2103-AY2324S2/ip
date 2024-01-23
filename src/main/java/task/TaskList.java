package task;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

/**
 * This class encapsulates a {@link Task} list.
 */
public class TaskList {
    /**
     * Stores the tasks.
     */
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Checks if the index is a valid index in this list.
     *
     * @param index the index of in the task list
     * @return true if the index is valid, otherwise false
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Add a to-do to this list.
     *
     * @param name the name of the to-do to add
     * @return the task that is added
     */
    public Task addTodo(String name) {
        Task task = new ToDo(name);
        tasks.add(task);
        return task;
    }

    /**
     * Add a deadline to this list.
     *
     * @param name the name of the to-do to add
     * @return the task that is added
     */
    public Task addDeadline(String name, String by) {
        Task task = new Deadline(name, by);
        tasks.add(task);
        return task;
    }

    /**
     * Add an event to this list.
     *
     * @param name the name of the to-do to add
     * @return the task that is added
     */
    public Task addEvent(String name, String from, String to) {
        Task task = new Event(name, from, to);
        tasks.add(task);
        return task;
    }

    /**
     * Mark the task as done.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     */
    public Task markTask(int index) {
        tasks.get(index).mark();
        return tasks.get(index);
    }

    /**
     * Mark the task as not done.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     */
    public Task unmarkTask(int index) {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the number of tasks in this task list.
     *
     * @return the size as an int
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, otherwise false
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Gets a human-readable description of this task list.
     *
     * @return this task list as a human-readable string
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            message.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return message.toString();
    }
}
