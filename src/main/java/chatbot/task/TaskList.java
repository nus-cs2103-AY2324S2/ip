package chatbot.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chatbot.storage.LocalStorage;
import chatbot.storage.TaskListMemento;
import chatbot.task.exception.OutOfBoundsException;
import chatbot.value.DateStringValue;

/**
 * This encapsulates a {@link Task} list.
 */
public final class TaskList {
    /** Stores the tasks. */
    private List<Task> tasks;

    /**
     * Constructor for this.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Copy constructor for this task list.
     *
     * @param taskList The task list to copy.
     */
    public TaskList(TaskList taskList) {
        this.tasks = taskList.copyTasks();
    }

    private List<Task> copyTasks() {
        return tasks
                .stream()
                .map(Task::copy)
                .collect(Collectors.toList());
    }

    /**
     * Saves the internal state of the task list.
     *
     * @return The saved memento.
     */
    public TaskListMemento saveState() {
        return new TaskListMemento(this);
    }

    /**
     * Restores the internal state of the task list.
     *
     * @param taskListMemento The memento to restore from.
     */
    public void restoreState(TaskListMemento taskListMemento) {
        tasks = taskListMemento.getSavedState().copyTasks();
        LocalStorage.saveTaskList(this);
    }

    /**
     * Checks if the index is a valid index in this list.
     *
     * @param index The index of in the task list.
     * @return True if the index is valid, otherwise false.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Adds a {@link ToDo} to this list.
     *
     * @param name The name of the {@link ToDo} to add.
     * @return The task that is added.
     */
    public Task addTodo(String name) {
        return add(new ToDo(name));
    }

    /**
     * Adds a task to this list, saving the task list to local storage.
     *
     * @param <T> The type of task to add.
     * @param newTask The task to add.
     * @return The task that is added.
     */
    public <T extends Task> Task add(T newTask) {
        tasks.add(newTask);
        LocalStorage.saveTaskList(this);
        return newTask;
    }

    /**
     * Adds a {@link Deadline} to this list.
     *
     * @param name The name of the {@link Deadline} to add.
     * @return The task that is added.
     */
    public Task addDeadline(String name, DateStringValue by) {
        return add(new Deadline(name, by));
    }

    /**
     * Adds an {@link Event} to this list.
     *
     * @param name The name of the {@link Event} to add.
     * @return The task that is added.
     */
    public Task addEvent(String name, DateStringValue from, DateStringValue to) {
        return add(new Event(name, from, to));
    }

    /**
     * Performs an operation on a {@link Task} in the list.
     *
     * @param index The 0-indexed index of the {@link Task}.
     * @param taskConsumer A {@link Consumer} that takes in a {@link Task}.
     * @return The task that the operation was performed on.
     * @throws OutOfBoundsException If the index is out of bounds. Note that one is added to this,
     *     so that the message is consistent with the 1-indexed input from the user.
     */
    private Task performTask(int index, Consumer<Task> taskConsumer) throws OutOfBoundsException {
        if (isEmpty()) {
            throw new OutOfBoundsException(index + 1, "The task list is empty.");
        }

        if (!isValidIndex(index)) {
            throw new OutOfBoundsException(index + 1, "The index must be between 1 and " + tasks.size() + ".");
        }

        Task task = tasks.get(index);
        taskConsumer.accept(task);
        LocalStorage.saveTaskList(this);
        return task;
    }

    /**
     * Marks the task as done.
     *
     * @param index The index of the task (0-indexed).
     * @return The task that is marked.
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task markTask(int index) throws OutOfBoundsException {
        return performTask(index, Task::mark);

    }

    /**
     * Marks the task as not done.
     *
     * @param index The index of the task (0-indexed).
     * @return The task that is marked.
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws OutOfBoundsException {
        return performTask(index, Task::unmark);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task (0-indexed).
     * @return The task that is marked.
     * @throws OutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws OutOfBoundsException {
        return performTask(index, tasks::remove);
    }

    /**
     * Finds matching {@link Task}(s) that matches the pattern.
     *
     * @param pattern The pattern to match with.
     * @return The sorted indexes in the task list that matches the pattern.
     */
    public int[] findMatchingTaskIndices(String pattern) {
        return IntStream
                .range(0, tasks.size())
                .filter(i -> tasks.get(i).isContainingPattern(pattern))
                .sorted()
                .toArray();
    }

    /**
     * Gets a message regarding the size of this {@link TaskList}.
     */
    public String getSizeMessage() {
        return String.format("Now you have %s task(s) in the list.", tasks.size());
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets a human-readable description of this {@link TaskList}.
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
     * Gets a human-readable description of this {@link TaskList},
     * filtered by indexes.
     *
     * @param sortedIndexes A sorted array of 0-indexed indexes.
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
