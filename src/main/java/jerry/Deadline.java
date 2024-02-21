package jerry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the chatbot application.
 * A deadline task is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a deadline task with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date for the task.
     */

    public Deadline(String description, String by) throws DateTimeParseException{
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone? 1 : 0) + " | " + description + " | " + by;
    }

    public boolean byIsNull() {
        return by == null;
    }

    @Override
    public boolean isScheduledForDate(LocalDate date) {
        return by.equals(date);
    }
}
