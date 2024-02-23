package eggy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eggy.exception.DuplicatedTaskException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** List of tasks. */
    private List<Task> tasks;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @throws DuplicatedTaskException If the task to be added is already in the task list.
     */
    public void addTask(Task task) throws DuplicatedTaskException {
        if (this.tasks.contains(task)) {
            throw new DuplicatedTaskException();
        }
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index Index of the task to be removed.
     * @return Task that was removed.
     */
    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a list of tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return List of tasks that match the keyword.
     */
    public List<Task> findMatchingTasks(String keyword) {
        return this.tasks.stream().filter(task -> task.getName().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        return IntStream.range(0, getSize())
                .mapToObj(i -> "  " + (i + 1) + "." + this.getTask(i))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns the string representation of the task list to be saved in a file.
     *
     * @return String representation of the task list to be saved in a file.
     */
    public String toFileString() {
        return this.tasks.stream()
                .map(task -> task.toFileString() + "\n")
                .collect(Collectors.joining());
    }
}
