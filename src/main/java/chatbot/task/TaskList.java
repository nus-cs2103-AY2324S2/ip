package chatbot.task;

import chatbot.storage.LocalStorage;
import chatbot.task.exception.OutOfBoundsException;
import chatbot.value.DateStringValue;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import java.util.function.Consumer;

/**
 * This class encapsulates a {@link Task} list.
 */
public final class TaskList {
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
    private boolean isValidIndex(int index) {
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
     * Performs an operation on a {@link Task} in the list
     *
     * @param index the index of the {@link Task}
     * @param taskConsumer a consumer that takes in a {@link Task}
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
     * Mark the task as done.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task markTask(int index) throws OutOfBoundsException {
        return performTask(index, Task::mark);

    }

    /**
     * Mark the task as not done.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws OutOfBoundsException {
        return performTask(index, Task::unmark);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the validated index of the task (0-indexed)
     * @return the task that is marked
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws OutOfBoundsException {
        return performTask(index, tasks::remove);
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
}
