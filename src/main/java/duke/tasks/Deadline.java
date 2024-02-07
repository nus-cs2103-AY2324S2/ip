package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline (String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    public Deadline (String description, boolean done, String by) {
        super(description);
        super.updateIsDone(done);
        this.by = parseDateTime(by);
    }

    @Override
    public String getSaveTask() {
        return "D | " + super.getSaveTask() + " | " + by.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }
}