package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadline extends Task {
    String type = "[D]";
    LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline.replace(" ", ""));
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDBString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "D|" + display + "|" + this.description + "|" + this.deadline;
    }
}
