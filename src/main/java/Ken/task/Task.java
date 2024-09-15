package ken.task;

import ken.exception.KenException;

/**
 * The Task class represents a general task.
 *
 * A task has a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the status icon for the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of description.
     *
     * @return String representation of description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task to its string representation.
     *
     * @return The string representation of the task.
     */

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to its file string representation.
     *
     * @return The file string representation of the task.
     */
    public String toFileString() {
        // " | 0 | task description"
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Parses a task from its file string representation.
     *
     * @param fileString The file string representation of the task.
     * @return The parsed task.
     * @throws KenException If the task type is invalid.
     */
    public static Task parseFromFileString(String fileString) throws KenException {
        // "T | 1 | read book"
        String[] parts = fileString.split(" \\| ");

        char taskType = parts[0].charAt(0);  // Extract task type
        int status = Integer.parseInt(parts[1]);  // Extract status as integer
        String description = parts[2];  // Extract description

        Task task;
        if (taskType == 'T') {
            task = new Todo(description);
        } else if (taskType == 'D') {
            task = new Deadline(description, parts[3]);
        } else if (taskType == 'E') {
            task = new Event(description, parts[3], parts[4]);
        } else {
            throw new KenException("Invalid task type");
        }

        // Set task status based on the parsed value
        if (status == 1) {
            task.markAsDone();
        }

        return task;
    }
}
