package patrick;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String name;
    private LocalDateTime by;
    private boolean isDone;
    private Tag tag;
    private static String IDENTIFIER = "[D]";

    private DateTimeFormatter dtfoutput = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.name = name;
        this.by = by;
        this.isDone = false;
        this.tag = new Tag();
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

    public void addTag(Tag tag) {
        this.tag = tag;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X]" + IDENTIFIER + " " + this.name + "(by " + dtfoutput.format(by) + ")" +
                    " " + tag.toString();
        } else {
            return "[ ]" + IDENTIFIER + " " + this.name + "(by " + dtfoutput.format(by) + ")" +
                    " " + tag.toString();
        }

    }

    public String getInput() {
        String mark;
        if (this.isDone) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s/deadline/%s/%s/%s", mark, name, by, tag.getInput());
        return str;
    }
}
