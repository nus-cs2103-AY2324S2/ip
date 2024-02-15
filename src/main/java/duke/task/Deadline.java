package duke.task;

import java.time.LocalDate;

/**
 * Subclass of task that represents deadlines.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs deadline object.
     *
     * @param description Description of task.
     * @param isDone Boolean value representing completion status.
     * @param by Due date of task. LocalDate object.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
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
        return String.join(",", TaskType.DEADLINE.toString(),
                super.getTokens(), this.by.format(Task.getDateFormat()));
    }

    /**
     * @inheritDoc
     *
     * @return Deadline date.
     */
    @Override
    public LocalDate getStartDateTime() {
        return this.by;
    }
}
