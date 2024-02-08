package youdon;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Youdon task management system.
 * This class provides methods for managing tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructs a new instance of the TaskList class with the specified list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param i The index of the task to remove.
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param task The task to add to the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }
}
