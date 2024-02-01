package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Contructs new deadlibe object with a description and a due date.
     *
     * @param description Brief description of task.
     * @param by LocalDateTine object
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String name, boolean isDone, LocalDate by) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.getDateFormat()) + ")";
    }

    /**
     * @inheritDoc
     *
     * @return description + isDone + from + to
     */
    @Override
    public String getTokens() {
        return String.join(",", "D", super.getTokens(), this.by.format(Task.getDateFormat()));
    }
}