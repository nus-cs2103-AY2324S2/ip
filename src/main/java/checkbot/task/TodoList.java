package checkbot.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import checkbot.exception.InvalidIndexException;

/**
 * Represents a list of tasks.
 */
public class TodoList {
    private final ArrayList<Task> taskList;

    public TodoList() {
        this.taskList = new ArrayList<>();
    }

    private TodoList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param i The index of the task to be marked as done.
     * @throws InvalidIndexException If the index is invalid.
     */
    public Task markTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= taskList.size()) {
            throw new InvalidIndexException(i + 1, taskList.size());
        }
        taskList.get(i).mark();
        return taskList.get(i);
    }

    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param i The index of the task to be unmarked.
     * @throws InvalidIndexException If the index is invalid.
     */
    public Task unmarkTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= taskList.size()) {
            throw new InvalidIndexException(i + 1, taskList.size());
        }
        taskList.get(i).unmark();
        return taskList.get(i);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param i The index of the task to be deleted.
     * @return The deleted task.
     * @throws InvalidIndexException If the index is invalid.
     */
    public Task deleteTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= taskList.size()) {
            throw new InvalidIndexException(i + 1, taskList.size());
        }
        Task deletedTask = taskList.get(i);
        taskList.remove(i);
        return deletedTask;
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public int getLength() {
        return taskList.size();
    }

    @Override
    public String toString() {
        return IntStream.range(0, taskList.size())
                .boxed()
                .reduce("", (
                        acc, index) -> String.format("%s\n%d. %s", acc, index + 1, taskList.get(index)), (
                        x, y) -> x + y)
                .strip();
    }

    /**
     * Formats the list of tasks for saving to a file.
     *
     * @return The formatted list of tasks.
     */
    public String formatForFile() {
        return taskList.stream()
                .reduce("", (
                        acc, task) -> String.format("%s\n%s",
                        acc, task.formatForFile()), (
                        x, y) -> x + y)
                .strip();
    }

    /**
     * Returns an array of Tasks whose descriptions contain the provided substring.
     *
     * @param substr The substring to look for Tasks.
     * @return An array containing filtered Tasks.
     */
    public TodoList find(String substr) {
        return new TodoList(
                new ArrayList<>(List.of(
                        taskList
                                .stream()
                                .filter(task -> task.nameContains(substr))
                                .toArray(Task[]::new)))
        );
    }
}
