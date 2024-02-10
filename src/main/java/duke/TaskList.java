package duke;


import java.util.ArrayList;
import java.util.List;
/**
 * Represents a list of tasks managed by the Duke application.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    /**
     * The current number of tasks in the list.
     */
    private int i = 0;

    /**
     * Constructs a TaskList object with the specified file path.
     *
     * @param filepath The file path for reading and writing tasks.
     */
    public TaskList(String filepath) {
        tasks = new ArrayList<>();
        storage = new Storage(filepath);
        tasks.addAll(storage.loadFromFile());
    }
    /**
     * Adds a task to the task list and saves it to the file.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveToFile(i, tasks); //index?
        i++;
    }
    /**
     * Deletes a task from the task list at the specified index and saves the updated list to the file.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        if (i > 0 && i < tasks.size()) {
            tasks.remove(index);
            storage.saveToFile(i, tasks);
            i--;
        }
    }
    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }
    /**
     * Sets a task at the specified index in the task list.
     *
     * @param index The index where the task should be set.
     * @param elem  The task to be set at the specified index.
     */
    public void setTask(int index, Task elem) {
        tasks.set(index, elem);
    }


}
