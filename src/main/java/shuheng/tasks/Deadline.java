package shuheng.tasks;

import java.time.LocalDateTime;

/**
 * This class represents a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for deadline
     * @param description A description of the task.
     * @param deadline When the task is due by (the deadline).
     */
    public Deadline(String description, LocalDateTime deadline, PriorityLevel priority) {
        super(description, "[D]", priority);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String getLogRepresentation() {
        String completeStatus = "F";
        if (this.isDone) {
            completeStatus = "T";
        }
        return "D" + "," + completeStatus + ","
            + this.description + "," + getPriority() + "," + this.getDeadline().toString();
    }

    @Override
    public String getTimeData() {
        return "(by: " + this.decomposeDateTime(this.deadline) + ")";
    }

}
