package duke.task;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task with a LocalDate variable by, to represent the deadline dated
 */
public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String desc, String by) throws DukeException {
        super(desc);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Oops! The date and time format you provided is not valid. Please enter in the format yyyy-MM-dd");
        }
    }

    public Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Deadline deadline) {
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
