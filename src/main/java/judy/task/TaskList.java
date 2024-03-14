package judy.task;

import java.util.ArrayList;

/**
 * The TaskList class represents a collection of Task objects.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList with an initial capacity of 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskList The list of tasks to be included in the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the current size of the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets the ArrayList containing the tasks in the TaskList.
     *
     * @return The ArrayList of tasks in the TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the Task at the specified index in the TaskList.
     *
     * @param taskId The index of the Task to retrieve.
     * @return The Task at the specified index.
     */
    public Task get(int taskId) {
        return this.taskList.get(taskId);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task The Task to be added to the TaskList.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param task The Task to be removed from the TaskList.
     */
    public void remove(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return {@code true} if the TaskList is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
