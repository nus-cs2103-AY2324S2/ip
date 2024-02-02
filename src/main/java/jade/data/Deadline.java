package jade.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The <code>Deadline</code> object represents a user task with a deadline date.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;

    /**
     * Class constructor specifying the deadline description and deadline date.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Another Class constructor.
     * Specifying the deadline description, deadline date, and the completion status.
     */
    public Deadline(String description, LocalDate deadlineDate, boolean isDone) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns a formatted string of the deadline date.
     */
    public String dateFormatter() {
        return deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * @inheritDoc This implementation checks whether the deadline date equals to the date.
     */
    @Override
    public boolean isSameDate(LocalDate date) {
        return date.equals(deadlineDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),  dateFormatter());
    }

    /**
     * @inheritDoc Adds the deadline date at the end.
     */
    @Override
    public String taskFormatter() {
        return String.format("D | %s | %s | %s\n", statusFormatter(), description,  dateFormatter());
    }
}
