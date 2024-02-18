package bmo.task;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {

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
}