package tasks;

/**
 * The `Task` class represents a generic task with a description and a completion status.
 * It includes methods to get the task description, mark the task as done or undone,
 * get the status icon, type icon, and the command format for saving to a file.
 */
public class Task {

    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a `Task` object with the specified description and initializes the completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }


    public String getDescription() {
        return this.description;
    }
    /**
     * Marks the task as done and prints a message indicating the completion.
     */
    public String markAsDone() {
        this.isDone = true;
        return (String.format("Congratulations! Task completed:\n [%s] %s", this.getStatusIcon(),
                this.toString()));
    }

    /**
     * Marks the task as undone and prints a message indicating the change.
     */
    public String maskAsUndone() {
        this.isDone = false;
        return (String.format("Task marked as undone:\n [%s] %s", this.getStatusIcon(),
                this.toString()));
    }

    /**
     * Gets the status icon for the task.
     *
     * @return The status icon ("X" if done, " " if undone).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the type icon for the task.
     *
     * @return The type icon (default is " ").
     */
    public String getTypeIcon() {
        return " ";
    }

    /**
     * Gets the command format for saving the task to a file.
     *
     * @return The command format for saving the task to a file (empty string for generic tasks).
     */
    public String getCommand() {
        return "";
    }
}
