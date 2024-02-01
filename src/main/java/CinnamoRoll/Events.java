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

    /**
     * Returns a string that describes the number of tasks in the list,
     * description of the (event) task, starting the and ending time of
     * the event task with getStatusIcon with task type and the marked status
     *
     * @param length denotes the length of the list
     */
    String added(int length) {
        return String.format("Got it. I've added this task:%n   " +
                "%s%nNow you have %d tasks in the list", this.toString(), length);
    }

    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.from.format(format), this.to.format(format));

    }
}