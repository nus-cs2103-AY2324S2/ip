package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that is added by the user.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * {@inheritDoc}
     * The deadline task also has a date field to specify the deadline time.
     * @param description the description of the deadline.
     * @param date the date of the deadline.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Changes the String representation of the deadline to displaying a D letter,
     * along with its status icon, its description and its date.
     * @return the specified string representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[D]" + super.toString() + " (by: " + this.date.format(outputFormatter) + ")";
    }

    @Override
    public boolean isOnThisDay(LocalDate date) {
        LocalDate taskDate = this.date.toLocalDate();
        return taskDate.equals(date);
    }
}
