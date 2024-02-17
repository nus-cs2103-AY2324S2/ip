package sleepy.tasks;

import java.time.format.DateTimeFormatter;

/**
 * This class is an abstract class for the tasks in the list.
 *
 * @author kjw142857
 */
public abstract class Task {
    // Offset of the task description to exclude whether it is done, i.e. "[ ]" and "[X]".
    public static final int TASK_DESCRIPTION_OFFSET = 4;
    // Format to convert dates to, which is the form d MMM yyyy (e.g. 1 Jan 2024).
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    // Description of the task (without timings).
    private String description;

    private Boolean isDone = false;

    /**
     * Constructor for the abstract class Task (will only be invoked by subclasses).
     *
     * @param description Description of the task details.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return Description of this task.
     */
    public String getDescription() {
        if (isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

    /**
     * Returns whether this task is done.
     *
     * @return Whether this task is done, converted to a string.
     */
    public String isDone() {
        return isDone.toString();
    }

    /**
     * Returns the raw description of this task.
     *
     * @return Raw description of this task.
     */
    public abstract String getRawDescription();
}
