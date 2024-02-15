package task;
import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added to the task list.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Marks a task as done in the task list.
     * @param index Index of the task to be marked as done.
     * @return Task that was marked as done.
     */
    public Task mark(int index) {
        Task task = this.list.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done in the task list.
     * @param index Index of the task to be marked as not done.
     * @return Task that was marked as not done.
     */
    public Task unMark(int index) {
        Task task = this.list.get(index);
        task.markNotDone();
        return task;
    }

    /**
     * Deletes a task from the task list.
     * @param index Index of the task to be deleted.
     * @return Task that was deleted.
     */
    public Task delete(int index) {
        return this.list.remove(index);
    }

    /**
     * Returns the list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks in the task list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword The keyword to search for.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
