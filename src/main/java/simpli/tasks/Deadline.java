package simpli.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task to know when is the task due by.
 */
public class Deadline extends Task implements Comparable<Task> {
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

        assert by.isAfter(LocalDateTime.now());
        this.by = by;
    }

    /**
     * Returns the deadline task as a comma-separated values (csv) String representation.
     *
     * @return String representing the csv value for the deadline task.
     */
    @Override
    public String toCsv() {
        return String.format("Deadline,%s,%s", super.toCsv(), this.by.format(FORMATTER));
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return this.by.compareTo(deadline.by);
        }
        return super.compareTo(o);
    }

    /**
     * Returns the deadline task String representation.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(FORMATTER));
    }
}
