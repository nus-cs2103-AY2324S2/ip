package Duke;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a collection of tasks and provides methods to manipulate the tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     * @throws DukeDataCorruptedException If there is an issue decoding the task data.
     */
    public TaskList(List<Task> tasks) throws DukeDataCorruptedException {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task in the TaskList as done based on its index.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).setDone(true);
    }

    /**
     * Marks a task in the TaskList as not done based on its index.
     *
     * @param index The index of the task to mark as not done.
     */
    public void markTaskAsNotDone(int index) {
        tasks.get(index).setDone(false);
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}

