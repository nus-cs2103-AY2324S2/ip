package task;
import java.time.LocalDateTime;

import util.CsvUtil;
import util.DateTimeUtil;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isMarked, String description, LocalDateTime by) {
        super(isMarked, description);
        this.by = by;
    }

    @Override
    public CsvUtil format() {
        return new CsvUtil("D", String.valueOf(super.isMarked), super.description,
            DateTimeUtil.format(by));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
            DateTimeUtil.format(by));
    }
    @Override
    public boolean equals(Object o) {
        // Step 1: Check if the objects are the same instance
        if (this == o) {
            return true;
        }

        // Step 2: Check if the object is null or of a different class
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // Step 3: Convert the object to a ToDo instance
        Deadline d = (Deadline) o;

        // Step 4: Compare the individual fields for equality
        return isMarked == d.isMarked && description.equals(d.description) && d.by.equals(by);
    }
}
