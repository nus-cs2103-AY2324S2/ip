package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event-type task. It includes a description, start date, and end
 * date.
 * This class extends the Task class, adding functionality specific to tasks
 * that have a start and end date.
 */
public class Event extends Task {

    private String start;
    private String end;

    /**
     * Constructs a new Event instance with a specified description, start date, and
     * end date.
     * 
     * @param description The text description of the event task.
     * @param start       The start date of the event.
     * @param end         The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.end = end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Constructs Event with datetime Strings directly.
     * 
     * @param description The text description of the event task.
     * @param start       The start date of the event.
     * @param end         The end date of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Generates a string representation of the event task, including its type
     * indicator "[E]",
     * completion status, description, and the start and end dates.
     * 
     * @return A string that represents the event task, showing its status,
     *         description, and the start and end dates.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public int compareTo(Task other) {
        if (!(other instanceof Event)) {
            return -1;
        }
        Event o = (Event) other;
        int startComp = this.start.compareTo(o.start);
        if (startComp != 0) {
            return startComp;
        }
        int endComp = this.end.compareTo(o.end);
        if (endComp != 0) {
            return endComp;
        }
        return super.compareTo(o);
    }

    @Override
    public String serialize() {
        return super.serialize() + "🦫" + start + "🦫" + end;
    }
}
