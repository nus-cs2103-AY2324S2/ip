package blu.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import blu.exception.IllegalParameterException;

/**
 * Represents a list of tasks. This class provides methods to manipulate and retrieve tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a predefined list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the task specified by the 1-based index is within the range of the TaskList.
     *
     * @param idx The 1-based index to check.
     * @return true if the task number is valid, false otherwise.
     */
    private boolean isValidIndex(int idx) {
        if (idx < 0 || idx >= tasks.size()) {
            return false;
        }
        return true;
    }

    /**
     * Retrieves the task at the specified 1-based task number.
     *
     * @param taskNumber The 1-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IllegalParameterException If the task number is invalid.
     */
    public Task getTask(int taskNumber) throws IllegalParameterException {
        int idx = taskNumber - 1;
        if (!isValidIndex(idx)) {
            throw new IllegalParameterException("Could not retrieve task number "
                + taskNumber + "\n"
                + "Please use the list command to view valid task numbers.");
        }
        return tasks.get(idx);
    }

    /**
     * Returns a list containing all tasks in the task list.
     *
     * @return The list of all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified 1-based task number.
     *
     * @param taskNumber The 1-based index of the task to delete.
     * @throws IllegalParameterException If the task number is invalid.
     */
    public void deleteTask(int taskNumber) throws IllegalParameterException {
        int idx = taskNumber - 1;
        if (!isValidIndex(idx)) {
            throw new IllegalParameterException("Could not delete task number "
            + taskNumber + "\n"
            + "Please use the list command to view valid task numbers.");
        }
        tasks.remove(idx);
    }

    /**
     * Searches through task list and returns a list of tasks that contain a specific substring.
     *
     * @param searchString The substring to search for within each task of the task list.
     * @return A list of task that contain the specified substring.
     */
    public List<Task> searchForTasksContaining(String searchString) {
        return tasks.stream()
                .filter(t -> t.getTitle().contains(searchString))
                .collect(Collectors.toList());
    }

    /**
     * Returns a string representation of the TaskList.
     * If the list is empty, returns the message "All tasks completed!".
     *
     * @return A string representation of the TaskList.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "All tasks completed!";
        }
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d %s", i + 1, tasks.get(i)))
                .collect(Collectors.joining("\n"));
    }
}
