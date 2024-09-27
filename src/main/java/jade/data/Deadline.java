package jade.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The <code>Deadline</code> object represents a user task with a deadline date.
 */
public class Deadline extends Task {
    protected LocalDateTime deadlineDateTime;

    /**
     * Class constructor specifying the deadline description and deadline date.
     */
    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Another Class constructor.
     * Specifying the deadline description, deadline date, and the completion status.
     */
    public Deadline(String description, LocalDateTime deadlineDateTime, boolean isDone) {
        super(description, isDone);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Returns a formatted string of the deadline date.
     */
    public String dateTimeFormatter() {
        return deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM d uuuu hh:mm a", Locale.UK));
    }

    /**
     * {@inheritDoc}
     * Checks whether the deadline date equals to the given date.
     */
    @Override
    public boolean isSameDate(LocalDate date) {
        return date.equals(deadlineDateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateTimeFormatter());
    }

    /**
     * {@inheritDoc}
     * Adds the deadline date at the end.
     */
    @Override
    public String taskFormatter() {
        return String.format("D | %s | %s | %s\n", statusFormatter(), description, dateTimeFormatter());
    }
}
