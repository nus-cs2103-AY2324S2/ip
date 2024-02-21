package jerry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the chatbot application.
 * An event task is a task that occurs at a specific time or over a specific period.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */

    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);

    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }

    public boolean dateTimeIsNull() {
        return from == null || to == null;
    }

    @Override
    public boolean isScheduledForDate(LocalDate date) {
        return (date.isEqual(ChronoLocalDate.from(from)) || date.isAfter(ChronoLocalDate.from(from))) && (date.isEqual(ChronoLocalDate.from(to)) || date.isBefore(ChronoLocalDate.from(to)));
    }
}
