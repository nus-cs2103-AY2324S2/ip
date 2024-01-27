package task;

import util.CSVUtil;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isMarked, String description, String by) {
        super(isMarked, description);
        this.by = by;
    }

    @Override
    public CSVUtil format() {
        return new CSVUtil("D", String.valueOf(super.isMarked), super.description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
