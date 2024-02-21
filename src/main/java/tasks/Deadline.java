package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_DATE_FORMAT);
    }

    public LocalDateTime getDueDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(Task.OUTPUT_DATE_FORMAT));
    }
}
