package panna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event subtask. Each event subtask consists of a
 * String input for the task and 2 LocalDate values which represents the start and end
 * of a task.
 */
public class Event extends Task {
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy");


    /**
     * Constructor method for event
     * @param input
     * @param start
     * @param end
     */
    public Event(String input, LocalDate start, LocalDate end) {
        super(input, start, end);

    }

    /**
     * Represents the string value for an event object
     * @return String representation of object.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + getStart().format(df) + " to " + getEnd().format(df) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }

        Event e = (Event) o;

        return this.getStart().equals(e.getStart()) && this.getEnd().equals(e.getEnd()) && super.equals(e);
    }

}
