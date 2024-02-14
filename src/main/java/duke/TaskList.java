package duke;
import java.util.ArrayList;

/**
 * Stores all the tasks in an array list which can be manioulated using add, delete, mark, unmark commands.
 */
public class TaskList {
    private ArrayList<duke.Task> listArr;

    public TaskList(ArrayList<duke.Task> listArr) {
        this.listArr = listArr;
    }

    /**
     * Returns the size of the array list.
     * @return int size
     */
    public int size() {
        return this.listArr.size();
    }

    /**
     * Returns the task at that index of the array list.
     * @return Task task
     */
    public duke.Task get(int idx) {
        return this.listArr.get(idx);
    }

    /**
     * Sets the task at that index to be done.
     * @param idx int index
     */
    public void setDone(int idx) {
        this.get(idx).setDone();
    }

    /**
     * Sets the task at that index to be undone.
     * @param idx int index
     */
    public void setUndone(int idx) {
        this.get(idx).setUndone();
    }

    /**
     * Deletes the task at that index.
     * @param idx int index
     */
    public void deleteTask(int idx) {
        this.listArr.remove(idx);
    }

    /**
     * Adds a task to the array list.
     * @param Task t
     */
    public void addTask(Task t) {
        this.listArr.add(t);
    }

    /**
     * Returns the toString() of the Task at that index.
     * @param idx
     * @return String toString
     */
    public String taskToString(int idx) {
        return this.get(idx).toString();
    }


}
