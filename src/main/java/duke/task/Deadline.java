package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task with a due date and time.
 */
public class Deadline extends Task {
    private LocalDateTime dueTime;
    private LocalDate dueDate;

    /**
     * Constructs a deadline object.
     *
     * @param taskName Name of task.
     * @param isDone Completion of task.
     * @param dueTime LocalDateTime representing due time of task.
     */
    public Deadline(String taskName, boolean isDone, LocalDateTime dueTime) {
        super(taskName, isDone);
        this.dueTime = dueTime;
    }

    /**
     * Constructor for deadline object.
     *
     * @param taskName Name of task.
     * @param isDone Completion of task.
     * @param dueDate LocalDate representing due date of task.
     */
    public Deadline(String taskName, boolean isDone, LocalDate dueDate) {
        super(taskName, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toFileString() {
        if (this.dueDate == null) {
            return "deadline|" + super.toFileString() + "|" + this.dueTime + "|" + super.tagToString();
        } else {
            return "deadline|" + super.toFileString() + "|" + this.dueDate + "|" + super.tagToString();
        }
    }

    @Override
    public String toString() {
        if (this.dueDate == null) {
            String timeFormat = this.dueTime.format(DateTimeFormatter.ofPattern("MMM d yyyy KK.mm a"));
            return "[D]" + super.toString()
                    + String.format(" (by: %s)", timeFormat)
                    + super.tagToString();
        } else {
            String timeFormat = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString()
                    + String.format(" (by: %s)", timeFormat)
                    + super.tagToString();
        }
    }
}
