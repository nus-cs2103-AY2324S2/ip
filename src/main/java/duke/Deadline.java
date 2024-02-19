package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String name;
    private LocalDateTime by;
    private boolean isDone;
    private static String IDENTIFIER = "[D]";

    private DateTimeFormatter dtfoutput = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.name = name;
        this.by = by;
        this.isDone = false;
    }

    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name);
        this.name = name;
        this.by = by;
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
            return "[X]" + IDENTIFIER + " " + this.name + "(by " + dtfoutput.format(by) + ")";
        } else {
            return "[ ]" + IDENTIFIER + " " + this.name + "(by " + dtfoutput.format(by) + ")";
        }

    }

    public String getInput() {
        String mark;
        if (this.isDone) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/deadline/%s/%s", mark, name, by);
        return str;
    }
}
