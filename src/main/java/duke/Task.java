package duke;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
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
    public String getDescription() {
        return this.description;
    }
    /**
     * Gets the status icon indicating whether the task is done or not.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return The string representation of the task for saving to a file.
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Creates a task from a string when loading from a file.
     *
     * @param fileString The string representation of the task from the file.
     * @return The Task object created from the file string.
     */
    public static Task createTaskFromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if (parts.length == 3) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task = new Task(description);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        }
        return null;
    }
}
