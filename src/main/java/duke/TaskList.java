package duke;
import java.util.ArrayList;
/**
 * Tasklist which is a simple array list containing the task class
 * @author Cedric
 */

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int length = 0;
    /**
     * Clears the list
     */
    public void clear() {
        taskList.clear();
        length = 0;
    }
    /**
     * Adds a task to the list
     * @param t the Task to be added
     */
    public void add(Task t) {
        taskList.add(t);
        length = length + 1;
    }
    /**
     * Deletes task from list based on sequence
     * @param numberToDelete the task to delete starting from 0
     * @return The task that was deleted from the list
     */
    public Task delete(int numberToDelete) {
        if (numberToDelete < length && numberToDelete >= 0) {
            Task t = taskList.remove(numberToDelete);
            length = length - 1;
            return t;
        }
        return null;
    }
    /**
     * Gets the task to return based on input int
     * @param numberToGet Task to return based on sequence
     * @return Task based on numberToGet
     */
    public Task get(int numberToGet) {
        if (numberToGet < length && numberToGet >= 0) {
            return taskList.get(numberToGet);

        }
        return null;
    }
    /**
     * Returns length of list
     * @return length of list
     */
    public int length() {
        return length;
    }
}
