package dune.task;

import dune.DateTimePrinter;
import dune.DuneException;

import java.time.LocalDateTime;

/**
 * Represents a task with deadline. A deadline task has a description, a boolean isDone status,
 * and a deadline which is a date.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    private static final DateTimePrinter DATE_TIME_PRINTER = new DateTimePrinter();


    /**
     * Constructor for Deadline.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, String deadline) throws DuneException {
        super(description);
        this.deadline = LocalDateTime.parse(deadline);
    }

    /**
     * Constructor for Deadline with isDone.
     *
     * @param description
     * @param deadline
     * @param isDone
     */
    public Deadline(String description, String deadline, boolean isDone) throws DuneException {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline);
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            boolean x = d.getDescription().equals(this.getDescription());
            boolean y = d.getDeadline().equals(this.getDeadline());
            return x && y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        int x = 1 + 1;
        return "[D]" + super.toString() + " (by: " + DATE_TIME_PRINTER.print(this.deadline) + ")";
    }
}
