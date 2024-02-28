package luna;

import java.util.ArrayList;

import luna.entry.ListEntry;


/**
 * Represents a list to track tasks. A task list contains listEntry to represent the tasks.
 */
public class TaskList {
    private ArrayList<ListEntry> list;

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
        return ((index < this.getSize()) && (index >= 0));
    }

    /**
     * Returns the size of the task list
     *
     * @return task list size
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Return a listEntry object in the task list
     *
     * @param index of the task in the list
     * @return the entry at a given index of the list
     */
    public ListEntry getEntry(int index) {
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

    /**
     * Checks if the entry at an index of the list is snoozable
     *
     * @param index index of the list
     * @return true if entry is snoozable
     */
    public boolean isEntrySnoozable(int index) {
        ListEntry ent = this.getEntry(index);
        return ent.isSnoozable();
    }

    /**
     * Snooze an entry if snoozable
     *
     * @param index index of the list
     * @param days number of days to snooze
     */
    public void snoozeEntry(int index, int days) {
        ListEntry ent = this.getEntry(index);
        if (ent.isSnoozable()) {
            ent.snoozeEntry(days);
        }
    }

}
