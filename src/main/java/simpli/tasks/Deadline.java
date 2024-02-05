package simpli.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime by;

    /**
     * Initializes a deadline task with the specified attributes.
     *
     * @param isDone boolean representing if a task is completed or not.
     * @param name String.
     * @param by Date when the task is due.
     */
    public Deadline(boolean isDone, String name, LocalDateTime by) {
        super(isDone, name);
        this.by = by;
    }

    public String toCsv() {
        return String.format("Deadline,%s,%s", super.toCsv(), this.by.format(FORMATTER));
    }

    /**
     * Returns the deadline task String representation.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(FORMATTER));
    }
}
