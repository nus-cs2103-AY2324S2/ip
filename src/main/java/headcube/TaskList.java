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
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list at a specific index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return  tasks.get(index);
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
     */
    public void mark(int taskNumber) {
        Task task = this.tasks.get(taskNumber - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index of the task in the list to be deleted.
     */
    public void delete(int taskNumber) {
        Task removedTask = this.tasks.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.\n");
    }
}
