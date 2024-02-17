package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private String name;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean done;
    private static String identifier = "[E]";

    private DateTimeFormatter dtfoutput = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.name = name;
        this.from = from;
        this.to = to;
        this.done = false;
    }

    public Event(String name, LocalDateTime from, LocalDateTime to, boolean done) {
        super(name);
        this.name = name;
        this.from = from;
        this.to = to;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X]" + identifier + " " + this.name + " (from " + dtfoutput.format(from) + " to " + dtfoutput.format(to) + ")";
        } else {
            return "[ ]" + identifier + " " + this.name + " (from " + dtfoutput.format(from) + " to " + dtfoutput.format(to) + ")";
        }
    }

    public String getInput() {
        String mark;
        if (this.done) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/event/%s/%s/%s", mark, name, from, to);
        return str;
    }

}
