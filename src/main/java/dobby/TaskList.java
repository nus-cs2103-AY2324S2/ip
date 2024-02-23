package dobby;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class is the list of tasks.
 * It provides methods to manipulate the tasks.
 *
 */
public class TaskList {
    
    private List<Task> tasks;

    /**
     * Constructs a TaskList instance.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) throws DukeException {
        if (tasks.contains(task)) {
            throw new DukeException("Task already exists in the list.\n");
        } else {
            tasks.add(task);
        }
    }

    /**
     * Deletes a task from the list by its number.
     *
     * @param number The number of the task to be deleted.
     */
    public void deleteTask(int number) {
        tasks.remove(number - 1);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets a task from the list by its number.
     *
     * @param number The number of the task to be received.
     * @return The task corresponding to the number.
     */
    public Task getTask(int number) {
        return tasks.get(number - 1);
    }

    /**
     * Unmarks a task.
     *
     * @param number The number of the task to be unmarked.
     */
    public void unmark(int number) {
        Task t = tasks.get(number - 1);
        t.markAsUnDone();
    }

    /**
     * Marks a task as done.
     *
     * @param number The number of the task to be marked as done.
     */
    public void mark(int number) {
        Task t = tasks.get(number - 1);
        t.markAsDone();
    } 

    public List<Task> findTasks(String word) {
        List<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.toString().contains(word)) {
                foundTasks.add(task);
            }
        }
    
        return foundTasks;
    }

}
