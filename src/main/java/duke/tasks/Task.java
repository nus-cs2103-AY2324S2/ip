package duke.tasks;

/**
 * The Task class represents a task with a description and a status, and provides methods to set and
 * get the status, convert the task to a string representation, and convert the task to a storage
 * string.
 */
public class Task {
    protected final String description;

    private boolean status;

    public Task(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.status;
    }

    /**
     * Converts a task object to its string representation for storage.
     * 
     * @return An empty string is being returned.
     */
    public String toStorageString() {
        return "";
    }

    /**
     * Returns a string representation of a task with its status and description.
     * 
     * @return Returns a string representation of a task.
     */
    @Override
    public String toString() {
        String checkBox = "[ ]";

        if (status)
            checkBox = "[X]";

        return checkBox + " " + description;
    }
}
