package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that is added by the user.
 */
public class Event extends Task {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * {@inheritDoc}
     * The deadline task also has a fromDate field to specify the starting date and,
     * a toDate field to specify the ending date.
     * @param description the description of the deadline.
     * @param fromDate the starting date of the event.
     * @param toDate the ending date of the event.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Changes the String representation of the event to displaying an E letter,
     * along with its status icon, its description, its starting and ending date.
     * @return the specified string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(outputFormatter) + " to: "
                + this.toDate.format(outputFormatter) + ")";
    }

    @Override
    public boolean isOnThisDay(LocalDate date) {
        LocalDate taskDate = this.fromDate.toLocalDate();
        return taskDate.equals(date);
    }
}
