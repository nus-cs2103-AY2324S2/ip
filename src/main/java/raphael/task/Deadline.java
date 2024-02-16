package raphael.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import raphael.command.Command;
import raphael.exception.RaphaelException;

/**
 * The deadline task class.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * The constructor of the deadline task.
     *
     * @param description the description of the deadline task.
     * @param deadline the deadline of this deadline task.
     * @throws RaphaelException exception exclusive to Raphael.
     */
    public Deadline(String description, String deadline) throws RaphaelException {
        super(description);
        try {
            this.deadline = LocalDateTime.parse(deadline, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.DEADLINE));
        }
    }

    /**
     * Overridden constructor of the deadline class for reading from file.
     * @param description the description of the deadline task.
     * @param deadline the deadline of the deadline task.
     * @param isDone the status of the deadline task.
     * @throws RaphaelException exception exclusive to Raphael.
     */
    public Deadline(String description, String deadline, boolean isDone) throws RaphaelException {
        super(description, isDone);
        try {
            this.deadline = LocalDateTime.parse(deadline, this.inputFormat);
        } catch (DateTimeParseException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.DEADLINE));
        }
    }

    /**
     * Returns a boolean value indicating if the current task deadline is exactly as the specified date.
     *
     * @param date the deadline used for the checking.
     * @return the boolean indicating if the deadline of current task is exactly as the specified one.
     */
    public boolean isDueBy(String date) {
        return this.deadline.isEqual(LocalDateTime.parse(date, inputFormat));
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(this.outputFormat));
    }
    @Override
    public String toFileFormat() {
        return String.format("D |&| %s |&| %s", super.toFileFormat(), this.deadline.format(this.inputFormat));
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline deadlineToCompare = (Deadline) o;
        return this.deadline.equals(deadlineToCompare.deadline) && super.equals(deadlineToCompare);
    }
}
