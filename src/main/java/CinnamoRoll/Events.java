package CinnamoRoll;

import java.time.*;
import java.time.format.DateTimeFormatter;

class Events extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    Events(String str, LocalDateTime from, LocalDateTime to) {
        super(str);
        this.from = from;
        this.to = to;
    }

    Events(String str, LocalDateTime from, LocalDateTime to, boolean marked) {
        super(str, marked);
        this.from = from;
        this.to = to;
    }

    String getStatusIcon() {
        return (this.is_marked ? "[E][X]" : "[E][ ]");
    }

    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.from.format(format), this.to.format(format));

    }

    String added(int length) {
        return String.format("Got it. I've added this task:%n   " +
                "%s%nNow you have %d tasks in the list", this.toString(), length);
    }
}