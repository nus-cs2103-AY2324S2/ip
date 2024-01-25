import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.list.add(t);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getLength() {
        return this.list.size();
    }

    /**
     * Marks a task as completed.
     *
     * @param i The index of the task to mark.
     * @return The marked task.
     */
    public Task markTask(int i) {
        Task t = this.list.get(i);
        t.mark();
        return t;
    }

    /**
     * Unmarks a completed task.
     *
     * @param i The index of the task to unmark.
     * @return The unmarked task.
     */
    public Task unmarkTask(int i) {
        Task t = this.list.get(i);
        t.unmark();
        return t;
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int i) {
        return this.list.remove(i);
    }

    /**
     * Returns a string representation of the TaskList.
     * Each task in the list is converted to a string and appended to the result.
     * Tasks are numbered and listed in the order they are stored in the list.
     */
    @Override
    public String toString() {
        StringBuilder displayString = new StringBuilder();
        int count = 1;
        for (Task t: list) {
            displayString.append(count).append(". ").append(t.toString()).append("\n");
            count++;
        }
        return displayString.toString();
    }
}
