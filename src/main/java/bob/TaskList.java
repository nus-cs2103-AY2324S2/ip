package bob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

import bob.exception.InvalidEventException;
import bob.exception.InvalidTaskIndexException;
import bob.exception.SavingException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Represents the list of tasks for remembered by the program. A <code>TaskList</code> object corresponds to
 * a list storing the tasks remembered by the program.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Returns an empty list of tasks to be used throughout the program.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns an initialised list of tasks to be used throughout the program.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks currently remembered by the program.
     *
     * @return The size of the list of tasks currently remembered by the program.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks after applying a specified filter.
     *
     * @param filter The filter to apply.
     * @return The filtered list of tasks.
     */
    private ArrayList<Task> listWithFilter(Predicate<Task> filter) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (filter.test(task)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns the list of tasks without any filter.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> list() {
        return listWithFilter(task -> true);
    }

    /**
     * Returns the list of tasks occurring on a specified day.
     *
     * @param date The date on which the returned tasks will occur.
     * @return The list of tasks occurring on the specified date.
     */
    public ArrayList<Task> listOnDate(LocalDate date) {
        return listWithFilter(task -> task.isOccurringOn(date));
    }

    /**
     * Returns the list of tasks due in a specified number of days.
     *
     * @param days The number of days in which the returned tasks will be due.
     * @return The list of tasks due in the specified number of days.
     */
    public ArrayList<Task> listDueIn(int days) {
        return listWithFilter(task -> task.isDueIn(days));
    }

    public ArrayList<Task> listKeyword(String keyword) {
        return listWithFilter(task -> task.hasKeyword(keyword));
    }

    /**
     * Adds a todo to the list of tasks.
     *
     * @param description The description of the todo to be added.
     * @return The added todo.
     */
    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param description The description of the deadline to be added.
     * @param by The due time of the deadline to be added.
     * @return The added deadline.
     */
    public Task addDeadline(String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param description The description of the event to be added.
     * @param from The start time of the deadline to be added.
     * @param to The end time of the deadline to be added.
     * @return The added event.
     * @throws InvalidEventException If the end time of the event is earlier than its start time.
     */
    public Task addEvent(String description, LocalDateTime from, LocalDateTime to) throws InvalidEventException {
        // Check whether event is invalid
        if (to.isBefore(from)) {
            throw new InvalidEventException();
        }

        // Add and return the event
        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    /**
     * Marks or unmarks a task with a specified task index.
     *
     * @param taskIndex The task index of the task to be marked or unmarked.
     * @param isDone Whether to mark or unmark the task.
     * @return The marked or unmarked task.
     * @throws InvalidTaskIndexException If the given task index is invalid.
     */
    public Task mark(int taskIndex, boolean isDone) throws InvalidTaskIndexException {
        // Check whether task index is out of bounds
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidTaskIndexException(String.valueOf(taskIndex + 1), getSize());
        }

        // Mark the task using isDone and return it
        Task task = tasks.get(taskIndex);
        task.setDone(isDone);
        return task;
    }

    /**
     * Deletes a task with a specified task index.
     *
     * @param taskIndex The task index of the task to be deleted.
     * @return The deleted task.
     * @throws InvalidTaskIndexException If the given task index is invalid.
     */
    public Task delete(int taskIndex) throws InvalidTaskIndexException {
        // Check whether task index is out of bounds
        if (taskIndex < 0 || taskIndex >= getSize()) {
            throw new InvalidTaskIndexException(String.valueOf(taskIndex + 1), getSize());
        }

        // Delete and return the task
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task;
    }

    /**
     * Updates the storage with the current state of the list of tasks.
     *
     * @param storage The storage to perform the update on.
     * @throws SavingException If an error occurred while updating the storage.
     */
    public void updateStorage(Storage storage) throws SavingException {
        storage.refresh(tasks);
    }
}
