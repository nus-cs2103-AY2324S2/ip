package blu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a title, start and end date/time.
 * Inherits from the {@link Task} class.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an Event task.
     *
     * @param title The title of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats the date and time of the event for display.
     *
     * @param by The LocalDateTime object to be formatted.
     * @return A string representation of the date and time in the format "MMM dd yyyy, HH:mm".
     */
    private String displayDate(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return by.format(formatter);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s", TASK_TYPE, this.isCompleted() ? "T" : "F",
                this.getTitle(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (From: %s To: %s)", TASK_TYPE, super.toString(),
                displayDate(from), displayDate(to));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        boolean isSameFromDateTime = from.equals(other.from);
        boolean isSameToDateTime = to.equals(other.to);
        return isSameFromDateTime && isSameToDateTime && super.equals(other);
    }
}
