package yapchit.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        String tag = super.getDone() ? "[X]" : "[ ]";
        return "[D]"
                + tag
                + " " + super.getName().strip()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    public LocalDate getBy() {
        return this.by;
    }
}
