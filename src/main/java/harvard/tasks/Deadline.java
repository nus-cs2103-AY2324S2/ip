package harvard.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, Boolean isDone) {
        super(description);
        this.by = by;
        if (isDone) super.mark();
    }

    @Override
    public String saveString() {
        return "D," + (super.isDone ? "1," : "0,") + super.getDescription() + "," +
                this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
