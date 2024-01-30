package drake.task;
import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate this list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a predefined list of tasks.
     *
     * @param tasks The ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted Task object.
     * @throws IndexOutOfBoundsException If the index is out of the range of the task list.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of bounds.");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of the range of the task list.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of bounds.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Provides access to the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of the range of the task list.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        getTask(index).isDone = true;
    }

    /**
     * Unmarks a task in the task list, indicating it is not done.
     *
     * @param index The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If the index is out of the range of the task list.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        getTask(index).isDone = false;
    }

}