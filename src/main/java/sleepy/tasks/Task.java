package sleepy.tasks;

import sleepy.tools.ResponseHandler;

/**
 * This class is an abstract class for the tasks in the list.
 *
 * @author kjw142857
 */
public abstract class Task {
    // Description of the task (without timings).
    private String description;
    // Raw description of the task with all details included.
    private String rawDescription;

    private boolean isDone = false;

    /**
     * Constructor for the abstract class Task (will only be invoked by subclasses).
     *
     * @param rawDescription Full description of the task (including labels and timings).
     * @param description Description of the task details.
     */
    public Task(String rawDescription, String description) {
        this.rawDescription = rawDescription;
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
        ResponseHandler.appendLineToResponse("Nice! I've marked this task as done:");
        ResponseHandler.appendLineToResponse(this.getDescription());
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        isDone = false;
        ResponseHandler.appendLineToResponse("OK, I've marked this task as not done yet:");
        ResponseHandler.appendLineToResponse(this.getDescription());
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
     * Returns the raw description of this task.
     *
     * @return Raw description of this task.
     */
    public String getRawDescription() {
        return this.rawDescription;
    }
}
