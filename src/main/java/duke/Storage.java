package duke;

import java.util.ArrayList;

/**
 * The Storage class represents a storage facility for tasks.
 */
public class Storage {

    /**
     * The list to store tasks.
     */
    public ArrayList<Task> storage;

    /**
     * Constructs a new Storage instance with an empty task list.
     */

    public Storage() {
        this.storage = new ArrayList<Task>();
    }

    /**
     * Adds a task to the storage.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.storage.add(t);
    }

    /**
     * Retrieves the number of tasks in the storage.
     *
     * @return The number of tasks in the storage.
     */
    public int size() {
        return this.storage.size();
    }

    /**
     * Retrieves a task from the storage at the specified index.
     *
     * @param i The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.storage.get(i);
    }

    /**
     * Retrieves the index of a task in the storage based on its description.
     *
     * @param s The description of the task.
     * @return The index of the task in the storage.
     */
    public int indexOf(String s) {
        return this.storage.indexOf(s);
    }


    /**
     * Removes and retrieves a task from the storage at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws DukeExceptions If there is an issue accessing the storage.
     */
    public Task pop(int index) throws DukeExceptions {
        Task t = this.storage.get(index);
        this.storage.remove(index);
        return t;
    }

    /**
     * Creates a formatted string representing the list of tasks in the storage.
     *
     * @return The formatted string representing the list of tasks.
     */
    public String printList() {
        String output = "";
        for (int i=0; i<this.storage.size(); i++) {
            output += String.format("%d. ", i+1);
            output += this.storage.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Creates a formatted string representing the output when a task is added to the storage.
     *
     * @param t The task that was added.
     * @return The formatted string representing the output.
     */
    public String addToListOutput(Task t) {
        String output = "Got it. I've added this task:\n" +
                String.format("  %s\nNow you have %d tasks in the list.", t.toString(), this.size());
        return output;
    }
}
