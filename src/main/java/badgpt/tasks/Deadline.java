package badgpt.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a task which has a deadline.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates a new Deadline object.
     *
     * @param description The description of the task (e.g. what must be done).
     * @param deadline The deadline by which this task must be completed.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String saveTask() {
        return "[D]" + super.toString() + " by: " + deadline;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isHappening(LocalDate date) {
        return deadline.isAfter(date) || deadline.isEqual(date);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
