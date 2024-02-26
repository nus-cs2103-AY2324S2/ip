package Luna;

import java.util.ArrayList;

/**
 * Represents a list to track tasks. A task list contains listEntry to represent the tasks.
 */
public class TaskList {
    ArrayList<ListEntry> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a given listEntry to the task list
     *
     * @param listEntry to be added to the task list
     */
    public void add(ListEntry listEntry) {
        list.add(listEntry);
    }


    /**
     * Deletes an entry from the task list based on a given index
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
     * Returns true if the given index is valid for the task list.
     * Else return false
     *
     * @param index of the task in the list
     * @return boolean whether the index is valid for the task list
     */
    public boolean isValidIndex(int index) {
        return ((index < this.size()) && (index >= 0));
    }

    /**
     * Returns the size of the task list
     *
     * @return task list size
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
     * Removes all the entries in the task list
     */
    public void clear() {
        this.list.clear();
        assert list.isEmpty() : "List should not contain any elements";
    }

}
