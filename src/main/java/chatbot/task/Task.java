package chatbot.task;

/**
 * Represents a task, a base class for all tasks.
 */
public class Task {

    public static final String DATETIME_FORMAT_OUTPUT = "MMM dd yyyy HH:mm";
    private String description;
    private boolean isCompleted;

    /**
     * Constructor for Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructor for Task.
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Gets the status of the task.
     * @return The status of the task.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task to a string.
     * @return The string representation of the task.
     */
    public String exportToSave() {
        return this.description;
    }

}

