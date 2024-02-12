package chatbot.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import chatbot.storage.LocalStorage;
import chatbot.task.exception.OutOfBoundsException;
import chatbot.value.DateStringValue;

/**
 * This encapsulates a {@link Task} list.
 */
public final class TaskList {
    /** Stores the tasks. */
    private final List<Task> tasks;

    /**
     * Constructor for this.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Checks if the index is a valid index in this list.
     *
     * @param index the index of in the task list
     * @return true if the index is valid, otherwise false
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Adds a {@link ToDo} to this list.
     *
     * @param name the name of the {@link ToDo} to add
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
     * Add a {@link Deadline} to this list.
     *
     * @param name the name of the {@link Deadline} to add
     * @return the task that is added
     */
    public Task addDeadline(String name, DateStringValue by) {
        return add(new Deadline(name, by));
    }

    /**
     * Add an {@link Event} to this list.
     *
     * @param name the name of the {@link Event} to add
     * @return the task that is added
     */
    public Task addEvent(String name, DateStringValue from, DateStringValue to) {
        return add(new Event(name, from, to));
    }

    /**
     * Performs an operation on a {@link Task} in the list
     *
     * @param index the index of the {@link Task}
     * @param taskConsumer a {@link Consumer} that takes in a {@link Task}
     * @return the task that the operation was performed on
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    private Task performTask(int index, Consumer<Task> taskConsumer) throws OutOfBoundsException {
        if (isEmpty()) {
            throw new OutOfBoundsException(index, "The task list is empty.");
        }

        if (isValidIndex(index)) {
            Task task = tasks.get(index);
            taskConsumer.accept(task);
            LocalStorage.saveTaskList(this);
            return task;
        }

        throw new OutOfBoundsException(index, "The index must be between 1 and " + tasks.size() + ".");
    }

    /**
     * Marks the task as done.
     *
     * @param index the index of the task (0-indexed)
     * @return the task that is marked
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task markTask(int index) throws OutOfBoundsException {
        return performTask(index, Task::mark);

    }

    /**
     * Marks the task as not done.
     *
     * @param index the index of the task (0-indexed)
     * @return the task that is marked
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws OutOfBoundsException {
        return performTask(index, Task::unmark);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the index of the task (0-indexed)
     * @return the task that is marked
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws OutOfBoundsException {
        return performTask(index, tasks::remove);
    }

    /**
     * Finds matching {@link Task}(s) that matches the pattern.
     *
     * @param pattern the pattern to match with
     * @return the indexes in the task list that matches the pattern
     */
    public int[] findMatchingTasks(String pattern) {
        return IntStream
                .range(0, tasks.size())
                .filter(i -> tasks.get(i).isContainingPattern(pattern))
                .sorted()
                .toArray();
    }

    /**
     * Gets a message regarding the size of this.
     *
     * @return a message containing information about the size of this
     */
    public String getSizeMessage() {
        return String.format("Now you have %s task(s) in the list.", tasks.size());
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, otherwise false
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
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
     * Gets a human-readable description of this task list,
     * filtered by indexes.
     *
     * @param sortedIndexes a sorted array of indexes (0-indexed)
     * @return this task list as a human-readable string
     */
    public String toString(int[] sortedIndexes) {
        StringBuilder message = new StringBuilder();
        for (int i = 0, j = 0; i < tasks.size() && j < sortedIndexes.length; i++) {
            if (sortedIndexes[j] == i) {
                message.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
                j++;
            }
        }
        return message.toString();
    }
}
