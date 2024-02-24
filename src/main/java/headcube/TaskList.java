package headcube;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a list of tasks. Provides functionality to manage tasks including
 * adding, marking, and deleting tasks from the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor of TaskList object, which creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public boolean add(Task task) {
        int initialSize = tasks.size();
        if (tasks.contains(task)) {
            return false;
        } else {
            tasks.add(task);
            return true;
        }
    }

    /**
     * Retrieves a task from the task list at a specific index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskNumber The index of the task in the list to be marked as done.
     * @return The string says the task is marked.
     */
    public String mark(int taskNumber) {
        Task task = this.tasks.get(taskNumber - 1);
        task.done();
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index of the task in the list to be deleted.
     * @return The string that says the task is deleted.
     */
    public String delete(int taskNumber) {
        Task removedTask = this.tasks.remove(taskNumber - 1);
        return "Noted. I've removed this task:\n  " + removedTask + "\nNow you have "
                + this.tasks.size() + " tasks in the list.\n";
    }

    /**
     * Searches for tasks whose descriptions contain the specified input keyword.
     * Creates and returns a new TaskList consisting of only the matching tasks.
     *
     * @param input The keyword to search for in the task descriptions.
     * @return A new TaskList containing only the tasks that match the search criteria.
     */
    public TaskList find(String input) {
        TaskList found = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(input)) {
                found.add(task);
            }
        }
        return found;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
