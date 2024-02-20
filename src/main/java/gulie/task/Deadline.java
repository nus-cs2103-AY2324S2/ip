package gulie.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * A constructor for a Deadline.
     * @param name
     * @param by
     */
    public Deadline(String name, LocalDateTime by) {
        this(name, by, false);
    }

    /**
     * A constructor for a Deadline.
     * @param name
     * @param by
     * @param mark
     */
    public Deadline(String name, LocalDateTime by, boolean mark) {
        super(name, mark);
        this.by = by;
    }

    @Override
    public boolean onDate(LocalDate date) {
        return by.toLocalDate().equals(date);
    }

    @Override
    public String toSaveString() {
        return String.format("D\t%s\t%s", super.toSaveString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toString(DateTimeFormatter dtf) {
        return String.format("[D]%s (by: %s)", super.toString(), dtf.format(by));
    }
}
