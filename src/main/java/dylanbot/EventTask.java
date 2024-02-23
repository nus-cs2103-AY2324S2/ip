package dylanbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a Task of type E == EventTask
 */
class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");

    /**
     * Creates a new EventTask with the specified description, from, and to timings
     *
     * @param desc The specified description
     * @param from The specified from time
     * @param to The specified to time
     */
    public EventTask(String desc, LocalDateTime from, LocalDateTime to) {
        super("E", desc);
        this.from = from;
        this.to = to;
    }

    public EventTask(String desc, ArrayList<String> tags, LocalDateTime from, LocalDateTime to) {
        super("E", desc, tags);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "] "
                + (this.checkCompleted() ? "[X]" : "[ ]")
                + " " + this.getDesc()
                + " (from: " + from.format(printFormat) + " to: " + to.format(printFormat) + ")"
                + "\n\ttag(s): " + this.getTags();
    }
}
