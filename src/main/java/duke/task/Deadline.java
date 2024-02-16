package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The class representing a deadline task.
 * */
public class Deadline extends Task {
    /* Type indicator for Deadline task. */
    String type = "[D]";

    /* Deadline of the current task. */
    LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        assert deadline != null;
        this.deadline = LocalDate.parse(deadline.replace(" ", ""));
    }

    @Override
    public String toString() {
        return this.type
            + this.display
            + " "
            + this.description
            + "(by: "
            + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + ")";
    }

    @Override
    public String toDbString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "D|" + display + "|" + this.description + "|" + this.deadline;
    }
}
