package dibo;

import java.util.ArrayList;

import dibo.task.Task;

/**
 * Class to store all the tasks.
 */
public class TaskList {
    private final ArrayList<Task> storage;
    private int count;

    /**
     * Constructor for the TaskList class.
     *
     * @param storage The ArrayList returned from loading from the text file.
     */
    public TaskList(ArrayList<Task> storage) {
        this.storage = storage;
        this.count = storage.size();
    }

    /**
     * Takes in a task and adds it into the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.storage.add(task);
        this.count++;
    }

    /**
     * Returns the string representation of the list to be displayed.
     *
     * @return The string representation of the list to be displayed.
     */
    public String getDisplayFormat() {
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < count; ++i) {
            list.append((i + 1));
            list.append(".");
            list.append(storage.get(i).toString());
            list.append("\n");
        }
        return list.toString();
    }

    /**
     * Returns the string representation of the list to be saved.
     *
     * @return The string representation of the list to be saved.
     */
    public String getSaveFormat() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            Task task = storage.get(i);
            list.append(task.getSaveFormat());
            list.append("\n");
        }
        return list.toString();
    }

    /**
     * Takes in an index, marks the task at that index as done
     * and return the string representation of that task.
     *
     * @param i The index of the task.
     * @return The string representation of the task.
     */
    public String markTask(int i) {
        Task task = storage.get(i - 1);
        task.markAsDone();
        return task.toString();
    }

    /**
     * Takes in an index, marks the task at that index as not done
     * and return the string representation of that task.
     *
     * @param i The index of the task.
     * @return The string representation of the task.
     */
    public String unmarkTask(int i) {
        Task task = storage.get(i - 1);
        task.markAsNotDone();
        return task.toString();
    }

    /**
     * Takes in an index, deletes the task at that index
     * and return the string representation of that task.
     *
     * @param i The index of the task.
     * @return The string representation of the task.
     */
    public String deleteTask(int i) {
        Task task = storage.get(i - 1);
        storage.remove(i - 1);
        this.count--;
        return task.toString();
    }

    /**
     * Returns the size of the TaskList to be shown to the user.
     *
     * @return The size of the TaskList.
     */
    public int getSize() {
        return this.count;
    }
}