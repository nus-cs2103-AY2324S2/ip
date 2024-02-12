package duke;

import java.time.LocalDateTime;

/**
 * Deadline Class is a type of task a user can create.
 * A date and time is required as an input as the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline Class
     * @param task
     * @param taskType
     * @param deadline
     */
    public Deadline(String task, String taskType, String deadline) {
        super(task, taskType);
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Returns a string representation of the Deadline for the user to view.
     * @return String representation of the Deadline.
     */
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask() + this.getPeriod();
    }

    /**
     * Returns a string representation of date and time of the deadline.
     * @return String representation of date and time of the deadline.
     */
    public String getPeriod() {
        return " (By: " + dateToString() + ")";
    }

    /**
     * Informs the user a new Deadline has been created.
     * @return String informing a new Deadline has been created.
     */
    public String getAnnouncement() {
        return "New Deadline created!";
    }

    /**
     * Returns a string representation of the Deadline for txt file saving purposes.
     * @return String representation of the Deadline for txt file saving purposes.
     */
    public String saveString() {
        return this.getTaskTypeSingle() + "|" + this.getStatusBinary() + "|" + this.getTask() + "|"
                + this.dateToString();
    }

    /**
     * Returns a string representation of the date and time of the deadline.
     * @return String representation of the date and time of the deadline.
     */
    public String dateToString() {
        return this.deadline.format(formatter).replace("T", " ");
    }

    /**
     * Returns the updated Deadline after postponing.
     * @param input
     * @return Updated Deadline after postponing.
     */
    public String postpone(String input) {
        this.deadline = LocalDateTime.parse(input, formatter);
        return this.getTask() + " has been postponed to "
                + this.deadline.format(formatter).replace("T", " ");
    }
}
