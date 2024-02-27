package bmo.task;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task implements Comparable<Task> {

    /**
     * Constructor for the ToDo class.
     *
     * @param task The task to be done.
     */
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns the formatted string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        return "T | " + done + " | " + super.toString() + "\n";
    }

    /**
     * Returns the type priority of the task.
     *
     * @return Integer representing the type priority of the task.
     */
    @Override
    public int getTypePriority() {
        return 0;
    }

    /**
     * Returns the comparison of the description of the tasks, used for sorting.
     *
     * @return Integer representing the comparison of the description of the tasks.
     */
    @Override
    public int compareTo(Task t) {
        if (t instanceof ToDo) {
            return this.description.compareTo(t.description);
        } else {
            return this.getTypePriority() - t.getTypePriority();
        }
    }
}