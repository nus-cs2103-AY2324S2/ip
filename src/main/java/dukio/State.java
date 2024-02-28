package dukio;

import java.util.ArrayList;

/**
 * Class that represents the state of the app.
 */
public class State {

    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task at the given index.
     * Returns null if tasks is empty or if the index is out of bounds.
     *
     * @param i The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int i) {
        if (i < 0 || i >= tasks.size()) {
            return null;
        }
        return tasks.get(i);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }
}
