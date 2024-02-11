package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = LocalDate.parse(by);
    }

    public String toStore() {
        // need to store status as well
        return "D | " + super.toStore() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
