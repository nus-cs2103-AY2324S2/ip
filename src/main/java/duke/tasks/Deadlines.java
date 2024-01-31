package duke.tasks;

import java.time.LocalDateTime;

/**
 * This class represents a task with a deadline.
 */
public class Deadlines extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for deadline
     * @param description A description of the task.
     * @param deadline When the task is due by (the deadline).
     */
    public Deadlines(String description, LocalDateTime deadline) {
        super(description, "[D]");
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
            + this.description + "," + this.getDeadline().toString();
    }

    @Override
    public String getTimeData() {
        return "(by: " + this.decomposeDateTime(this.deadline) + ")";
    }

}
