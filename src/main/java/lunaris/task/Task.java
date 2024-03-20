package lunaris.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tagName;

    /**
     * Constructor for Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagName = "untagged";
    }

    /**
     * Constructor for Task with status.
     *
     * @param isDone status of task.
     * @param description Description of task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
        this.tagName = "untagged";
    }

    /**
     * Method to mark or unmark task.
     *
     * @return "X" if marking task, " " if unmarking task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method to set status to marked.
     *
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Method to set status to unmarked.
     *
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Method to return description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void replaceTag(String tag) {
        this.tagName = tag;
    }

    /**
     * Method to format task for storing in storage file.
     *
     * @return formatted string representation of task for file storage.
     */
    public String toFile() {
        return "";
    }

    /**
     * Method to return string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
