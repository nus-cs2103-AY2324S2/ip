package riz.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a timeframe.
 * The from and to fields represent the start and end date and time
 * of the task.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String event, String from, String to) {
        super(event);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        assert from != null : "event /from date/time cannot be null";
        assert to != null : "Deadline /to date/time cannot be null";
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * String representation of a Event Task.
     * @return the String includes a 'E' to indicate a "Event" task.
     * The start and end dates and times are represented
     * in a dd MMM yyyy hh:mm 12-hour format.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "E" + super.toString() + " | " + this.from.format(outputFormatter) + " - " + this.to.format(outputFormatter);
    }
}