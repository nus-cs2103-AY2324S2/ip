package seedu.duke.common;

import java.util.List;

import seedu.duke.task.Task;


/**
 * Represents the tasks created by the user and also implements methods to modify the tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor of TaskList
     *
     * @param tasks the initial tasks of the users
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the tasks created by the users
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task with a given index
     *
     * @param index The index of the task in the task list to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks that users have
     *
     * @return the number of tasks that users have
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Returns a task by giving the index of the task in the task list
     *
     * @param index The index of the task
     * @return The task queried
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
