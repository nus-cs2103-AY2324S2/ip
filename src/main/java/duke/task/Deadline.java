package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Type icon for Deadline task */
    private static final String TYPE = "D";
    /** Deadline or due date of this task */
    protected LocalDateTime dueDate;

    public Deadline(String name, LocalDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ":::" + this.dueDate;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String printDate = dueDate.format(formatter);
        return "[" + TYPE + "]" + super.toString() + " (by: " + printDate + ")";
    }

}
