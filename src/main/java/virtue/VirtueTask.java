package virtue;

/** Represents a task that can be handled by the chatbot. */
abstract public class VirtueTask {
    /** The description of the task. */
    private String description;

    /** An indicator whether the task is done or not. */
    private boolean isDone;

    /**
     * Creates a new task, by default, it is not done.
     *
     * @param description The description of the task.
     */
    public VirtueTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon of the task, X if done, none if not done.
     *
     * @return Status icon of task.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks the task as not done. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains a keyword or not.
     *
     * @param keyword The keyword to be searched in the description.
     * @return True if the description contains the keyword, and false if not.
     */
    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Retrieves the string representation of this task
     * to be used for printing the list.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Retrieves the string representation of this task
     * to be used for saving the task in a file.
     *
     * @return The file format of this task.
     */
    public String fileFormat() {
        if (isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }
}
