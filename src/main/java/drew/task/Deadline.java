package drew.task;

import java.time.LocalDate;
/**
 * This class represents the Deadline task.
 */
public class Deadline extends Task {
    /**
     * Stores the date in "YYYY-MM-DD" format.
     */
    private LocalDate date;
    /**
     * Constructor for the Deadline task.
     * @param description Description of the deadline.
     * @param date Do-by date of the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }
    /**
     * Returns the type, status and description of the Deadline task.
     * @return String in task list format.
     */
    @Override
    public String toStatusString() {
        return String.format("[D]%s (by: %s)", super.toStatusString(), this.date);
    }
    /**
     * Converts the task into string format for the save file.
     * @return String in save file format.
     */
    @Override
    public String toSaveFormatString() {
        String status = (super.isDone) ? "1" : "0";
        return String.format("D | %s | %s | %s\n", status, super.toString(), this.date);
    }

    @Override
    public boolean isEqual(Task task) {
        if (!(task instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) task;
        if (deadline == this) {
            return true;
        }
        if (!deadline.date.equals(this.date)) {
            return false;
        }
        if (!deadline.description.equals(this.description)) {
            return false;
        }
        assert this.description.equals(deadline.description) && deadline.date.equals(this.date);
        return true;
    }

    /**
     * Checks if this deadline task is due todau or after today.
     * @return True if deadline is due today or after today.
     */
    public boolean isUpcoming() {
        LocalDate now = LocalDate.now();
        boolean beforeDue = date.isAfter(now);
        return beforeDue;
    }
}
