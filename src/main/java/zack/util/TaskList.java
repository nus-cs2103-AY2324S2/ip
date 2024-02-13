package zack.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import zack.ZackException;
import zack.tasks.Deadline;
import zack.tasks.Task;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to be added cannot be null";
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws ZackException If the index is out of range.
     */
    public Task deleteTask(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("Task index is out of range.");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws ZackException If the index is out of range.
     */
    public Task getTask(int index) throws ZackException {
        if (index < 0 || index >= tasks.size()) {
            throw new ZackException("Task index is out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Gets all tasks in the TaskList.
     *
     * @return An ArrayList of all tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Searches for tasks in the current TaskList containing a specific keyword.
     *
     * This method iterates through the tasks in the current TaskList and checks
     * if each task's description contains the specified keyword. If a task's description
     * contains the keyword, it is added to a new TaskList, which is then returned as
     * the result of the search operation.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A new TaskList containing tasks whose descriptions contain the specified keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Sorts the list of tasks alphabetically by their description.
     *
     * @param ascOrDesc a string indicating whether to sort in ascending ("asc") or descending ("desc") order.
     */
    public void sortByAlphabetical(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            tasks.sort(Comparator.comparing(Task::getDescription));
        } else {
            tasks.sort(Comparator.comparing(Task::getDescription).reversed());
        }
    }

    /**
     * Sorts the list of deadlines by the deadline time.
     *
     * @param ascOrDesc a string indicating whether to sort in ascending ("asc") or descending ("desc") order.
     * @return a sorted list of deadlines according to the specified order.
     */
    public List<Deadline> sortDeadlines(String ascOrDesc) {
        Stream<Deadline> deadlineStream = this.tasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task);

        if ("asc".equalsIgnoreCase(ascOrDesc)) {
            return deadlineStream
                    .sorted(Comparator.comparing(Deadline::getBy))
                    .collect(Collectors.toList());
        } else {
            return deadlineStream
                    .sorted(Comparator.comparing(Deadline::getBy).reversed())
                    .collect(Collectors.toList());
        }
    }

    /**
     * Sorts the list of tasks by their added time.
     *
     * @param ascOrDesc a string indicating whether to sort in ascending ("asc") or descending ("desc") order.
     */
    public void sortByAddTime(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            tasks.sort(Comparator.comparing(Task::getAddedTime));
        } else {
            tasks.sort(Comparator.comparing(Task::getAddedTime).reversed());
        }
    }
}
