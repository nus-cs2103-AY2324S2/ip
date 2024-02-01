package dylanbot;

import dylanbot.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EventTask extends Task {
    public LocalDateTime from, to;
    DateTimeFormatter printFormat  = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");
    public EventTask(String type, String desc, LocalDateTime from, LocalDateTime to) {
        super(type, desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type + "] "
                + (completed ? "[X]" : "[ ]")
                + " " + desc
                + " (from: " + from.format(printFormat) + " to: " + to.format(printFormat) + ")";
    }
}
