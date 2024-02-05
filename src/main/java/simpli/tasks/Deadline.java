package simpli.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime by;

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
