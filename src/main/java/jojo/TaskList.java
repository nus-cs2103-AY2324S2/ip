package jojo;
import java.util.ArrayList;

/**
 * Stores all the tasks in an array list which can be manioulated using add, delete, mark, unmark commands.
 */
public class TaskList {
    private ArrayList<Task> listArr;

    public TaskList(ArrayList<Task> listArr) {
        this.listArr = listArr;
    }

    /**
     * Returns the size of the array list.
     * @return int size
     */
    public int size() {
        return listArr.size();
    }

    /**
     * Returns the task at that index of the array list.
     * @return Task task
     */
    public Task get(int idx) {
        return listArr.get(idx);
    }

    /**
     * Sets the task at that index to be done.
     * @param idx int index
     */
    public void setDone(int idx) {
        get(idx).setDone();
    }

    /**
     * Sets the task at that index to be undone.
     * @param idx int index
     */
    public void setUndone(int idx) {
        get(idx).setUndone();
    }

    /**
     * Deletes the task at that index.
     * @param idx int index
     */
    public void deleteTask(int idx) {
        listArr.remove(idx);
    }

    /**
     * Adds a task to the array list.
     * @param t Task
     */
    public void addTask(Task t) {
        listArr.add(t);
    }

    /**
     * Returns the toString() of the Task at that index.
     * @param idx int
     * @return String toString
     */
    public String taskToString(int idx) {
        return get(idx).toString();
    }


}
