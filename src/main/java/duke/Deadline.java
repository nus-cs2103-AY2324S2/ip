package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")) + ")";
    }
}
