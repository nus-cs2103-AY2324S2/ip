package ukecat;

/**
 * Represents a generic task in the UkeCat application.
 * Acts as the base class for specific task types like ToDo, Deadline, and Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified status, description, and isDone value.
     *
     * @param isDone      The status of the task ("0" for not done, "1" for done).
     * @param description The description of the task.
     */
    public Task(String isDone, String description) {
        this.isDone = isDone.equals("1");
        this.description = description;
    }

    /**
     * Sets the task as done or not done based on the provided MarkType.
     *
     * @param markType The type of marking (MARK for done, UNMARK for not done).
     */
    public String setDone(MarkType markType) {
        String notif = "";
        switch (markType) {
        case MARK:
            this.isDone = true;
            notif = "Nice! I've marked this task as done:\n";
            break;
        case UNMARK:
            this.isDone = false;
            notif = "OK, I've marked this task as not done yet:\n";
            break;
        }
        FileManager.updateTasks();
        return String.format("  %s    %s", notif, this);
    }

    /**
     * Gets the integer representation of the isDone status (1 for done, 0 for not done).
     *
     * @return The integer representation of the isDone status.
     */
    public int getIntIsDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Gets the status icon for the task ("[X] " for done, "[ ] " for not done).
     *
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
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
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
