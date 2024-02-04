package dylanbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");
    public EventTask(String desc, LocalDateTime from, LocalDateTime to) {
        super("E", desc);
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
                + (this.isCompleted() ? "[X]" : "[ ]")
                + " " + this.getDesc()
                + " (from: " + from.format(printFormat) + " to: " + to.format(printFormat) + ")";
    }
}
