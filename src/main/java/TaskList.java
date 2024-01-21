import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

/**
 * This class encapsulates a list of tasks.
 */
public class TaskList {
    /**
     * Stores the tasks.
     */
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Checks if the index is a valid index in the list.
     * @param index the index of in the task list
     * @return true if the index is valid, otherwise false
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Add a task to the list.
     * @param name the name of the task to add
     */
    public void addTask(String name) {
        tasks.add(new Task(name));
    }

    /**
     * Mark the task as done.
     * @param index the index of the task (0-indexed)
     */
    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).mark();
        }
    }

    /**
     * Mark the task as not done.
     * @param index the index of the task (0-indexed)
     */
    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).unmark();
        }
    }

    /**
     *
     * @param index the 0-indexed.
     * @return the index of the task (0-indexed)
     */
    public String getTask(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index).toString();
        }
        return "";
    }

    /**
     * @return the task list as a human-readable string.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            message.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return message.toString();
    }
}
