package duke.tasklist;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks. This class provides methods to manipulate
 * and query the list of tasks.
 */

public class TaskList {
    protected ArrayList<Task> list;
    
    /**
     * Constructs a new TaskList using the provided list of tasks.
     *
     * @param list The initial list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    
    /**
     * Returns the list of tasks.
     *
     * @return The current list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }
    
    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        this.getList().add(t);
    }
    
    /**
     * Deletes a task from the list.
     *
     * @param t The task to delete.
     */
    public void delete(Task t) {
        this.getList().remove(t);
    }
    
    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return this.getList().size();
    }
    
    /**
     * Retrieves a task by its position in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        return index >= 0 && index < this.size() ? this.getList().get(index) : null;
    }
    
    /**
     * Checks if a specific task is contained in the list.
     *
     * @param t The task to check for.
     * @return true if the task is in the list, otherwise false.
     */
    public boolean contains(Task t) {
        return this.getList().contains(t);
    }
    
    /**
     * Searches for tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A string containing a list of tasks that match the keyword, or a message if no tasks match.
     */
    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (Task t: this.getList()) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                num += 1;
                sb.append(num).append(".").append(t.toString()).append(System.lineSeparator());
            }
        }
        if (num == 0) {
            sb.append("No tasks match your find query for: ").append(keyword);
        }
        return sb.toString();
    }
    
}
