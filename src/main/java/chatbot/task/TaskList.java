package chatbot.task;

import chatbot.storage.LocalStorage;
import chatbot.value.DateStringValue;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates a {@link Task} list.
 */
public class TaskList {
    /**
     * Stores the tasks.
     */
    private final List<Task> tasks;

    /**
     * Constructor for this.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for this.
     *
     * @param tasks takes in a task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

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
        return add(new ToDo(name));
    }

    /**
     * Adds a task to this list, saving the task list to local storage.
     *
     * @param <T> the type of task to add
     * @param task the task to add
     * @return the task that is added
     */
    public <T extends Task> Task add(T task) {
        tasks.add(task);
        LocalStorage.saveTaskList(this);
        return task;
    }

    /**
     * Add a deadline to this list.
     *
     * @param name the name of the to-do to add
     * @return the task that is added
     */
    public Task addDeadline(String name, DateStringValue by) {
        return add(new Deadline(name, by));
    }

    /**
     * Add an event to this list.
     *
     * @param name the name of the to-do to add
     * @return the task that is added
     */
    public Task addEvent(String name, DateStringValue from, DateStringValue to) {
        return add(new Event(name, from, to));
    }

    /**
     * Mark the task as done.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     */
    public Task markTask(int index) {
        tasks.get(index).mark();
        LocalStorage.saveTaskList(this);
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
        LocalStorage.saveTaskList(this);
        return tasks.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     */
    public Task deleteTask(int index) {
        Task task = tasks.remove(index);
        LocalStorage.saveTaskList(this);
        return task;
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

    /**
     * Parse a task list item from a human-readable string.
     *
     * @param readableString the task list item as a human-readable string
     * @return the task
     * @throws IllegalStateException If the regex doesn't match the pattern.
     */
    public static Task parseTaskListItem(String readableString) throws IllegalStateException {
        Matcher matcher = Pattern
                .compile("\\d+\\.(?<task>.*)")
                .matcher(readableString);

        matcher.find();
        String parsedString = matcher.group("task").trim();
        return Task.parseTask(parsedString);
    }
}
