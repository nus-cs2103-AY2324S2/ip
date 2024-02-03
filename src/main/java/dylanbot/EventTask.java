package dylanbot;

import dylanbot.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EventTask extends Task {
    public LocalDateTime from, to;
    DateTimeFormatter printFormat  = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");
    public EventTask(String desc, LocalDateTime from, LocalDateTime to) {
        super("E", desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "] "
                + (this.isCompleted() ? "[X]" : "[ ]")
                + " " + this.getDesc()
                + " (from: " + from.format(printFormat) + " to: " + to.format(printFormat) + ")";
    }
}
