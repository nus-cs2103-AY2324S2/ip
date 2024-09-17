package chatbot;

import java.io.IOException;
import java.util.ArrayList;

import chatbot.exceptions.AlreadyMarkedException;
import chatbot.exceptions.AlreadyUnmarkedException;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor that retrieves the list of tasks from the file system.
     *
     * @param store The instance of Storage responsible for doing file IO.
     * @throws IOException If there is an issue with file IO.
     * @throws ClassNotFoundException If there is an issue with deserialising.
     */
    public TaskList(Storage store) throws IOException, ClassNotFoundException {
        this.tasks = store.readFromStore();
    }

    /**
     * Getter for the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Adds a Task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Marks a given task in the list.
     *
     * @param i The index of the task to mark.
     * @throws AlreadyMarkedException If the task is already marked.
     */
    public void markTask(int i) throws AlreadyMarkedException {
        this.tasks.get(i).mark();
    }

    /**
     * Unmarks a given task in the list.
     *
     * @param i The index of the task to unmark.
     * @throws AlreadyUnmarkedException If the task is not marked.
     */
    public void unmarkTask(int i) throws AlreadyUnmarkedException {
        this.tasks.get(i).unmark();
    }

    /**
     * Removes a task from the list.
     *
     * @param i The index of the task to remove.
     * @return The task that was removed.
     */
    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Filters tasks by string.
     *
     * @param s The search string.
     * @return The tasks with description containing that string.
     */
    public ArrayList<Task> filterByString(String s) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.contains(s)) {
                res.add(task);
            }
        }

        return res;
    }

    /**
     * Saves the instance's current state into the file system using the provided Storage object.
     *
     * @param store The Storage object to use.
     * @throws IOException If there was an issue with saving to hard disk.
     */
    public void saveToStore(Storage store) throws IOException {
        store.saveToStore(this.tasks);
    }
}
