package Luna;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list to track tasks. A tasklist contains listEntry to represent the tasks.
 */
public class TaskList {
    ArrayList<ListEntry> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a given listEntry to the tasklist
     *
     * @param listEntry to be added to the tasklist
     */
    public void add(ListEntry listEntry) {
        list.add(listEntry);
    }


    /**
     * Deletes a entry from the tasklist based on a given index
     *
     * @param index of the task in the list
     */
    public void delete(int index) {
        list.remove(index);
    }

    /**
     * Mark the task, changing its check to true
     *
     * @param index of the task in the list
     */
    public void mark(int index) {
        list.get(index).markEntry();
    }

    /**
     * Unmark the task, changing its check to false
     *
     * @param index of the task in the list
     */
    public void unmark(int index) {
        list.get(index).unmarkEntry();
    }

    /**
     * Returns true if the given index is valid for the tasklist.
     * Else return false
     *
     * @param index of the task in the list
     * @return boolean whether the index is valid for the tasklist
     */
    public boolean isValidIndex(int index) {
        return ((index < this.size()) && (index >= 0));
    }

    /**
     * Returns the size of the tasklist
     *
     * @return tasklist size
     */
    public int size() {
        return list.size();
    }

    /**
     * Return a listEntry object in the task list
     *
     * @param index of the task in the list
     * @return the entry at a given index of the list
     */
    public ListEntry get(int index) {
        return list.get(index);
    }

    /**
     * Returns true if the list is empty.
     * Else return false;
     *
     * @return boolean whether the task list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes all the entries in the tasklist
     */
    public void clear() {
        this.list.clear();
    }

}
