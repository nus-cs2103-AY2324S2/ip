package ukecat;

/**
 * Represents a generic task in the UkeCat application.
 * Acts as the base class for specific task types like ToDo, Deadline, and Event.
 */
public abstract class Task {
    private String description;
    private TaskStatus status;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }

    /**
     * Constructs a new Task with the specified status, description, and isDone value.
     *
     * @param status      The status of the task ("0" for not done, "1" for done).
     * @param description The description of the task.
     */
    public Task(TaskStatus status, String description) {
        this.status = status;
        this.description = description;
    }

    /**
     * Sets the task as done or not done based on the provided MarkType.
     *
     * @param markType The type of marking (MARK for done, UNMARK for not done).
     */
    public String setDone(MarkType markType) {
        String notif;
        switch (markType) {
        case MARK:
            this.status = TaskStatus.COMPLETE;
            notif = "Nice! I've marked this task as done:\n";
            break;
        case UNMARK:
            this.status = TaskStatus.NOT_DONE;
            notif = "OK, I've marked this task as not done yet:\n";
            break;
        default:
            notif = "Unknown markType encountered\n";
        }
        FileManager.updateTasks();
        return String.format("  %s    %s", notif, this);
    }

    /**
     * Gets the {@link TaskStatus} enum representing the task's status.
     *
     * @return The {@link TaskStatus} enum representing the task's status.
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return status == TaskStatus.COMPLETE ? "[X]" : "[ ]";
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
