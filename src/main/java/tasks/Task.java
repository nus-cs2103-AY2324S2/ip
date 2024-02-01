package tasks;

/**
 * Generic Task class used as a parent for other tasks
 */
public class Task {
    protected final String name;
    protected boolean complete;

    /**
     * Constructor for a Task
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
        this.complete = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletionStatus() {
        return this.complete;
    }

    /**
     * Toggles the completion status of a task
     * If it is marked, then it will become unmarked
     * Similarly, if it is unmarked, it will become marked
     */
    public void toggleCompletion() {
        if (complete) {
            complete = false;
            return;
        }
        complete = true;
    }

    protected String completionDisplay() {
        if (complete) {
            return "[X]";
        }
        return "[ ]";
    }

    protected String taskTypeDisplay() {
        return "[this shouldn't show]";
    }
    public String toString() {
        return String.format("%s%s %s", this.taskTypeDisplay(), this.completionDisplay(), this.name);
    }

    /**
     * Creates a string to be used in the format that is stored in the hard disc
     * @return The string containing the task formatted for the storage format
     */
    public String storeFormat() {
        String completeFormat = complete ? "1" : "0";
        return String.format("%s | %s | %s", "Err", completeFormat, name);
    }
}
