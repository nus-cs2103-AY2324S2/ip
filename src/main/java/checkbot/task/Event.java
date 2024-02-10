package checkbot.task;

import java.util.Objects;

/**
 * Represents an event task in the task list.
 * An event task is a task that contains a "from" and "to" date.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructor for an Event.
     *
     * @param name The name of the Event.
     * @param from When the Event started.
     * @param to   When the Event ends.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = tryParseDate(from);
        this.to = tryParseDate(to);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String formatForFile() {
        return String.format("E | %s | %s | %s", super.formatForFile(), this.from, this.to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event) || !super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }
}
