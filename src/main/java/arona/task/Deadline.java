package arona.task;

import arona.exception.AronaInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a "Deadline" task,
 * which is a task with a deadline time attached.
 *
 * @author Maximilliano Utomo
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    protected LocalDate deadline;

    /**
     * A public constructor for the task.Deadline.
     * @param desc - the description of the task
     * @param deadline - the deadline of the task
     */
    public Deadline(String desc, String deadline) throws AronaInvalidDateException {
        super(desc);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new AronaInvalidDateException("");
        }
    }

    @Override
    public String toDataFormat() {
        return "D|" + super.toDataFormat() + "|" + this.deadline;
    }

    /**
     * Represent the task into a String format applicable for printing output.
     * Uses an extra [D] to represent a task.Deadline.
     * @return A String representation of the task.Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
