package thames.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for tasks with deadline.
 */
public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            return super.equals(o) && ((Deadline) o).by.equals(this.by);
        }
        return false;
    }
}
