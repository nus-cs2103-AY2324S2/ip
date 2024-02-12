package scribbles.tasklist;

import java.util.ArrayList;

import scribbles.task.Task;

/**
 * This class represents the user's task list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to delete from the task list.
     * @throws IndexOutOfBoundsException If index does not exist in the task list.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        taskList.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task at index from the task list.
     *
     * @param index Index of the task to get.
     * @return Task at index.
     * @throws IndexOutOfBoundsException If the index does not exist in the task list.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }
}
