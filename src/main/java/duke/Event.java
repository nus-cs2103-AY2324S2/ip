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
    private boolean isDone;
    private static String IDENTIFIER = "[E]";

    private DateTimeFormatter dtfoutput = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.name = name;
        this.from = from;
        this.to = to;
        this.isDone = false;
    }

    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name);
        this.name = name;
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X]" + IDENTIFIER + " " + this.name + " (from " + dtfoutput.format(from) + " to " + dtfoutput.format(to) + ")";
        } else {
            return "[ ]" + IDENTIFIER + " " + this.name + " (from " + dtfoutput.format(from) + " to " + dtfoutput.format(to) + ")";
        }
    }

    public String getInput() {
        String mark;
        if (this.isDone) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/event/%s/%s/%s", mark, name, from, to);
        return str;
    }

}
