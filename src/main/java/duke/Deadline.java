package duke;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String saveToText() {
        return String.format("D | %s | %s | %s", this.isDone ? 1 : 0, this.description,
                Utils.convertDateTimeToString(this.by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Utils.printTime(by) + ")";
    }
}
