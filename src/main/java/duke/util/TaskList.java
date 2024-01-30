package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list to hold all added tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList instance by providing the ArrayList.
     *
     * @param list Array to hold Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructs a TaskList instance by assigning a new ArrayList.
     *
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds task to list.
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Returns number of Tasks in list.
     *
     * @return Size of ArrayList.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Returns if list is empty.
     *
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Removes Task at index num from list.
     *
     * @param num Index of Task to be deleted.
     * @return Task that is removed from list.
     */
    public Task delete(int num) {
        return this.list.remove(num);
    }

    /**
     * Returns header followed by index and the corresponding Tasks in list.
     * If no item in list, returns a different message.
     *
     * @return String of all Tasks in list.
     */
    public String print() {
        int count = 1;
        if (this.isEmpty()) {
            return "\t No task in list.";
        }
        StringBuilder sb = new StringBuilder("\t Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\t\t " + count + "." + list.get(i).printTask() + (i == list.size() - 1 ? "" : "\n"));
            count++;
        }
        return sb.toString();
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns Task at index specified.
     *
     * @param index Index of Task to retrieve.
     * @return Task at index in list.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
