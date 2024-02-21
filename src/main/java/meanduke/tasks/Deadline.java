package meanduke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class represents a Task with a deadline.
 */
public class Deadline extends Task {

    private static final String TYPE_SYMBOL = "[D]";
    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;

    /**
     * Constructs a new deadline Task with the specified description, completion status, and deadline.
     *
     * @param description  Description of the deadline Task
     * @param isDone       boolean value that determines if the initialised deadline Task is completed or not
     * @param deadlineDate Deadline date of the Task in format "YYYY-MM-DD"
     * @param deadlineTime Deadline time of the Task in format "HH:MM", or null.
     */
    public Deadline(String description, boolean isDone, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description, TYPE_SYMBOL, isDone);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String saveString() {
        return "DEADLINE" + "\n"
                + super.saveString() + "\n"
                + this.deadlineDate
                + (this.deadlineTime == null ? "" : ";" + this.deadlineTime);
    }

    @Override
    public String toString() {
        String deadlineString = this.deadlineDate.getDayOfMonth() + " "
                + this.deadlineDate.getMonth().toString() + " "
                + this.deadlineDate.getYear()
                + (this.deadlineTime == null ? "" : " " + this.deadlineTime);
        return super.toString() + " (by: " + deadlineString + ")";
    }
}
