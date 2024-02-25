package util;

import exceptions.ChillChiefException;
import tasks.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the ChillChief application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList object with specified tasks.
     *
     * @param taskList The list of tasks to add to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds a specific task to the task list.
     *
     * @param task The task to add to the task list.
     */
    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a specific task from the task list given its index.
     *
     * @param index The index of the task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets a particular task from the task list given its index.
     *
     * @param index The index of the task to get.
     * @return The particular task at the given index.
     * @throws ChillChiefException If the given index is out fo bounds.
     */
    public Task getTask(int index) throws ChillChiefException {
        if (index > this.tasks.size() - 1) {
            throw new ChillChiefException("Your index is out of bounds!");
        }
        return tasks.get(index);
    }

    /**
     * Gets all the tasks in the task list.
     *
     * @return The list of tasks in the task list.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Gets the current number of tasks in the task list.
     *
     * @return The number of tasks currently in the task list.
     */
    public int getTaskListLength() {
        return tasks.size();
    }
}