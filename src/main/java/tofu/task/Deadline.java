package tofu.task;

import tofu.TofuException;
import tofu.command.AddCommand;
import tofu.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a task with a deadline.
 * This task type includes a LocalDate variable 'by' to represent the deadline date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     *
     * @param desc The description of the task.
     * @param by The deadline of the task in yyyy-MM-dd format.
     * @throws TofuException If the provided date format is invalid.
     */
    public Deadline(String desc, String by) throws TofuException {
        super(desc);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new TofuException(Ui.invalidDateError());
        }
    }

    /**
     * Constructs a new Deadline task with the specified description, status, and deadline date.
     *
     * @param desc The description of the task.
     * @param isDone The status of the task.
     * @param by The deadline of the task in yyyy-MM-dd format.
     * @throws TofuException If the provided date format is invalid.
     */
    public Deadline(String desc, boolean isDone, String by) throws TofuException {
        super(desc, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new TofuException(Ui.invalidDateError());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return super.equals(deadline) && by.equals(deadline.by);
        } else {
            return false;
        }
    }

    public String toStore() {
        // need to store status as well
        return "D | " + super.toStore() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
