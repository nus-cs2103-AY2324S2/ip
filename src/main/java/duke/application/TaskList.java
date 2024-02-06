package duke.application;
import duke.task.Task;
import java.util.ArrayList;

/**
 * TaskList - Represents a list of tasks.
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
     * Gets specific task.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Gets the list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the total number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasks() {
        return tasks.size();
    }
}
