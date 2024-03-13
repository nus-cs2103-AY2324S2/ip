package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The class representing a deadline task.
 * */
public class Deadline extends Task {
    /* Type for Deadline task. */
    public static final String TYPE = "D";
    /* Type indicator for Deadline task. */
    public static final String TYPE_INDICATOR = "[D]";
    /* Deadline of the current task. */
    private final LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        assert deadline != null;
        this.deadline = LocalDate.parse(deadline.replace(" ", ""));
    }

    @Override
    public String toString() {
        return TYPE_INDICATOR
            + getDisplay()
            + " "
            + getDescription()
            + "(by: "
            + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + ")";
    }

    @Override
    public String toDbString() {
        return TYPE + super.toDbString() + this.deadline;
    }
}
