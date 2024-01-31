package Duke.Task.Deadlines;

import Duke.Task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class contains the functions for deadline tasks.
 * @author Tang Hao Liang
 */
public class Deadlines extends Task {
    protected LocalDate by;

    /**
     * Constructor that updates description and date for the task.
     *
     * @param description Deadline's description
     * @param by Task's deadline.
     */
    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFile() {
        if (isDone) {
            return "D|1|" + description + "|" + by;
        } else {
            return "D|0|" + description + "|" + by;
        }
    }
}
