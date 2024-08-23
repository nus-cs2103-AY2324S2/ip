package jivox.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event represents a task with a start and end time.
 * It extends Task.
 */
public class Event extends Task {

    private LocalDateTime from;

    private LocalDateTime to;

    /**
     * Creates a new Event with the given description, start and end time.
     *
     * @param content The task description.
     * @param from The start time.
     * @param to The end time.
     */
    public Event(String content, LocalDateTime from, LocalDateTime to) {
        super(content);
        this.from = from;
        this.to = to;
    }


    public String getType() {
        return "E";
    }

    /**
     * Gets the start time for this event.
     *
     * @return The start time.
     */
    public String getStart() {
        return this.from.toString();
    }

    public LocalDateTime getDeadline() {
        return this.to;
    }

    /**
     * Gets the save format string for this Event.
     *
     * @return The save format string.
     */
    @Override
    public String saveFormat() {
        return this.getType() + " | " + (this.getStatus() ? "1" : "0")
                + " | " + this.getDescription() + " | "
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " to " + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " tag " + (this.getTag().isEmpty() ? "None" : this.getTag());
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")"
                + (this.getTag().isEmpty() ? "" : " #" + this.getTag());
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            return this.getDescription().equalsIgnoreCase(e.getDescription())
                    && this.from.equals(e.from) && this.to.equals(e.to);
        }
        return false;
    }
}
