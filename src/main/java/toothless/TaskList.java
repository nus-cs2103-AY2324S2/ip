package toothless;

import java.util.ArrayList;
import java.util.List;

import toothless.tasks.Task;

/**
 * Represents a list of tasks.
 * This class is responsible for managing the tasks in the list.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructs a TaskList with the specified list of tasks.
     * @param taskList The list of tasks to be used to construct the TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     * @param t The task to be added to the list.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Removes the task at the specified index from the list.
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        assert index > 0;
        taskList.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the list.
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index > 0;
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     * @return The number of tasks currently in the list.
     */
    public int size() {
        return taskList.size();
    }
}
