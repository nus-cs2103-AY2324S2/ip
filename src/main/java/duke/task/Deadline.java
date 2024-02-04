package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.helpers.MyDateTime;


/**
 * duke.task.Deadline class with atttribute of by which is deadline of a task
 */
public class Deadline extends Task {
    /** duke.task.Deadline of a task */
    private LocalDateTime by;

    /**
     * Constructor of deadline class.
     *
     * @param description describe the deadline task.
     * @param by deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of deadline class.
     *
     * @param description describe the deadline task.
     * @param by deadline of the task.
     * @param isDone
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns String representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + MyDateTime.englishFormatter(this.by) + ")";
    }

    /**
     * Returns String representation for storage.
     *
     * @return String representation for storage of duke.task.Deadline task.
     */
    @Override
    public String toStorageString() {
        return CommandType.DEADLINE.toString() + " , "
                + super.toStorageString() + " , " + MyDateTime.formatter(this.by);
    }

    /**
     * Checks deadline.
     *
     * @param date
     * @return true if event deadline is same as given date.
     */
    @Override
    public boolean checkDate(LocalDate date) {
        return this.by.toLocalDate().equals(date);
    }
}
