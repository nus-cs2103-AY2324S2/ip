package checkbot.task;

/**
 * Represents an event task in the task list.
 * An event task is a task that contains a "from" and "to" date.
 */
public class Event extends Task {
    private final String from;
    private final String to;

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
}
