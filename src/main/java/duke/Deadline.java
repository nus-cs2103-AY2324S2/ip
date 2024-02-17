package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String name;
    private LocalDateTime by;
    private boolean done;
    private static String identifier = "[D]";

    private DateTimeFormatter dtfoutput = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.name = name;
        this.by = by;
        this.done = false;
    }

    public Deadline(String name, LocalDateTime by, boolean done) {
        super(name);
        this.name = name;
        this.by = by;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X]" + identifier + " " + this.name + "(by " + dtfoutput.format(by) + ")";
        } else {
            return "[ ]" + identifier + " " + this.name + "(by " + dtfoutput.format(by) + ")";
        }

    }

    public String getInput() {
        String mark;
        if (this.done) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/deadline/%s/%s", mark, name, by);
        return str;
    }
}
