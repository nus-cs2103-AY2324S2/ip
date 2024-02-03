package simpli.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter FORMATTER =DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(boolean isDone, String name, LocalDateTime by) {
        super(isDone, name);
        this.by = by;
    }

    public String toCsv() {
        return String.format("Deadline,%s,%s", super.toCsv(), this.by.format(FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(FORMATTER));
    }
}
