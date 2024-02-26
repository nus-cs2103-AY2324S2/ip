package bmo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is a deadline.
 */
public class Deadlines extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructor for the Deadlines class.
     *
     * @param task     The task to be done.
     * @param deadline The deadline of the task.
     */

    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        assert deadline != null : "Deadline cannot be null";
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

    /**
     * Returns the comparison of the deadline of the tasks, used for sorting.
     *
     * @return Integer representing the comparison of the deadline of the tasks.
     */
    @Override
    public int compareTo(Task t) {
        if (t instanceof Deadlines) {
            Deadlines d = (Deadlines) t;
            return this.deadline.compareTo(d.deadline);
        } else {
            return super.compareTo(t);
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDeadline() + ")";
    }

}
