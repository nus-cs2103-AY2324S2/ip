package util;

import java.io.IOException;
import java.util.ArrayList;

import task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage;

    /**
     * Constructs a TaskList object.
     *
     * @throws IOException if there is an error initializing the storage.
     */
    public TaskList() throws IOException {
        this.storage = new Storage(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to be added.
     * @throws IOException if there is an error writing to the storage.
     */
    public void add(Task t) throws IOException {
        this.tasks.add(t);
        storage.writeToFile(tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param i the index of the task to be deleted.
     * @return the deleted task.
     * @throws IOException if there is an error writing to the storage.
     */
    public Task delete(int i) throws IOException {
        Task t = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        storage.writeToFile(tasks);
        return t;
    }

    /**
     * Gets the size of the task list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param i the index of the task to be marked.
     * @return the marked task.
     * @throws IOException if there is an error writing to the storage.
     */
    public Task mark(int i) throws IOException {
        Task t = this.tasks.get(i - 1).mark();
        storage.writeToFile(tasks);
        return t;
    }

    /**
     * Marks a task as undone.
     *
     * @param i the index of the task to be unmarked.
     * @return the unmarked task.
     * @throws IOException if there is an error writing to the storage.
     */
    public Task unmark(int i) throws IOException {
        Task t = this.tasks.get(i - 1).unmark();
        storage.writeToFile(tasks);
        return t;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(i + 1).append(".").append(this.tasks.get(i).toString());
            if (i != this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
