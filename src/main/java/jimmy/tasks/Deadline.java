package jimmy.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructor for jimmy.tasks.Deadline class.
     *
     * @param taskName Name of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String taskName, String deadline, boolean isCompleted)
            throws DateTimeParseException, IllegalArgumentException {
        super(taskName, isCompleted);
        this.deadline = parseStringtoLocalDate(deadline);

        if (this.deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time of the event.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Details regarding the deadline.
     *
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseLocalDatetoString(this.deadline) + ")";
    }

    /**
     * Format of the deadline to be saved in the file.
     *
     * @return String representation of a deadline.
     */
    public String toFileString() {
        return String.format("%s | %d | %s | %s", "D", Objects.equals(super.getStatus(), "X") ? 1 : 0,
                super.getDesc(), this.deadline.format(informalDateFormat));
    }
}
