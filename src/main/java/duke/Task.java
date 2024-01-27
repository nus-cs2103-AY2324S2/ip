package duke;

/**
 * Represents a task with a description and a completion status.
 * This class serves as the base class for more specific types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * including its type, completion status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns a string representation of the task formatted for file storage,
     * including its type, completion status, and description.
     *
     * @return A string formatted for file storage.
     */
    public String toFileString() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a Task instance from the string representation of the task stored in the file.
     *
     * @param str The string representation of the task stored in the file.
     * @return The Task instance from the string representation of the task stored in the file.
     */
    public static Task fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("T")) {
            return null;
        }
        String description = parts[2].trim();
        boolean isDone = parts[1].trim().equals("1");
        Task task = new Task(description);
        if (isDone) task.markAsDone();
        return task;
    }

}
