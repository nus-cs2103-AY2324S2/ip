package whisper;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in the Whisper application.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskList The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws WhisperException If the index is invalid.
     */
    public void deleteTask(int index) throws WhisperException {
        checkIndex(index);
        Task removedTask = taskList.remove(index);
        System.out.println("Task deleted: " + removedTask.getDescription());
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws WhisperException If the index is invalid.
     */
    public void markTaskAsDone(int index) throws WhisperException {
        checkIndex(index);
        taskList.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws WhisperException If the index is invalid.
     */
    public void markTaskAsUndone(int index) throws WhisperException {
        checkIndex(index);
        taskList.get(index).markAsUndone();
    }

    /**
     * Gets a copy of the task list.
     *
     * @return A copy of the task list.
     */
    public ArrayList<Task> getTaskList() {
        // Return a copy to avoid direct manipulation of the internal list
        return new ArrayList<>(taskList);
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws WhisperException If the index is invalid.
     */
    public Task getTask(int index) throws WhisperException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new WhisperException("Invalid task number.");
        }
    }

    /**
     * Checks if the given index is valid for the task list.
     *
     * @param index The index to check.
     * @throws WhisperException If the index is invalid.
     */
    private void checkIndex(int index) throws WhisperException {
        if (index < 0 || index >= taskList.size()) {
            throw new WhisperException("Invalid task number.");
        }
    }
}
