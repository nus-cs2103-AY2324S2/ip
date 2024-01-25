/**
 * This Object stores all Task objects created in the main interface.
 * Uses the ArrayList data structure to store the Task, and allows for manipulation,
 * such as addition, deletion, and editing Tasks.
 */

import java.util.ArrayList;

public class TaskStorage {
    private ArrayList<Task> sl;

    /**
     * Constructs an empty ArrayList to store Task objects.
     */
    public TaskStorage() {
        this.sl = new ArrayList<>();
    }

    /**
     * Adds an Task object to the end of the ArrayList.
     *
     * @param t The Task object to be added to the ArrayList.
     */
    public void addTask(Task t) {
        this.sl.add(t);
    }

    /**
     * Removes an Task object from the ArrayList.
     *
     * @param id The id number of element to be removed from the ArrayList.
     */
    public String removeTask(int id) {
        Task t = this.sl.get(id - 1);
        this.sl.remove(id - 1);
        return t.toString();
    }

    /**
     * Edits the specified Task to change its completedness.
     *
     * @param id The id number of element to be edited.
     * @param mark To mark as complete or to unmark.
     */
    public String markTask(int id, boolean mark) {
        Task t = this.sl.get(id - 1);
        if (mark) {
            t.mark();
        }
        else {
            t.unmark();
        }
        return t.toString();
    }
    /**
     * Returns the size of the ArrayList.
     */
    public int size() {
        return this.sl.size();
    }

    /**
     * Returns a string representation of this TaskStorage.
     * This includes all the Task name, and conditions of each task.
     * 
     * @return a string representation of this TaskStorage.
     */
    @Override
    public String toString() {
        String totalAns = "";
        for (int i = 0; i < sl.size(); i ++) {
            totalAns += (i + 1) + "." + sl.get(i);
            totalAns += "\n";
        }
        totalAns += "______________________________________";
        return totalAns;
    }
}