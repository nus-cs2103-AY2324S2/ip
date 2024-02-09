package friendlytool.task;

import friendlytool.process.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String name, boolean isDone, Date by) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " | " + by.toSaveFormat() + "\n";
    }
}
