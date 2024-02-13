package toothless;

import java.util.ArrayList;
import java.util.List;

import toothless.tasks.Task;

/**
 * Represents a list of tasks. This class provides functionalities to manage tasks such as
 * adding, removing, and retrieving tasks from the list.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     * @param taskList A list of tasks to be managed.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList.
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
