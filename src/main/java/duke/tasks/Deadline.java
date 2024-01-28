package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String n, LocalDate by) {
        super(n);
        this.by = by;
    }

    public Deadline(String n, boolean d, LocalDate by) {
        super(n, d);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                                super.toString(),
                                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                            );
    }
}
