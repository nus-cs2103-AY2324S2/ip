package bmo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is a deadline.
 */
public class Deadlines extends Task{

    protected LocalDateTime deadline;

    /**
     * Constructor for the Deadlines class.
     *
     * @param task The task to be done.
     * @param deadline The deadline of the task.
     */
    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy', 'h:mma"));
    }

    /**
     * Returns the formatted string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        String deadlineStr = this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return "D | " + done + " | " + super.toString() + " | " + deadlineStr + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDeadline() + ")";
    }

}
