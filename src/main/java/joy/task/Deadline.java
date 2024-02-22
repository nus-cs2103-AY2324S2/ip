package joy.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the task list.
 * A Deadline task is a task with a by time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate by;
    protected String originalBy;


    /**
     * Constructs a Deadline task with the given by time.
     *
     * @param description The description of the Deadline task.
     * @param by The by time of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.originalBy = by;
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a string representation of the Deadline task for saving to a file.
     *
     * @return A formatted string representing the Deadline task for saving to a file.
     */
    @Override
    public String toFileString() {
        // Format: D | 0/1 | description | by date
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.originalBy;
    }

    /**
     * Returns by time of the Deadline task.
     *
     * @return by time of the Deadline task.
     */
    public String getBy() {
        return " (by: " + this.by.format(OUTPUT_DATE_FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getBy();
    }


    /**
     * @param t The task to be checked
     * @return if the task is a duplicate
     */
    @Override
    public boolean equals(Task t) {
        if (!(t instanceof Deadline)) {
            return false;
        }

        return t.toString().equals(this.toString());
    }
}
