package duke.task.deadlines;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;

/**
 * This class contains the functions for deadline tasks.
 * @author Tang Hao Liang
 */
public class Deadlines extends Task {
    protected LocalDateTime by;

    /**
     * Constructor that updates description and date for the task.
     *
     * @param description Deadline's description
     * @param by Task's deadline.
     */
    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byText = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));

        return "  [D]" + super.toString() + " (by: " + byText + ")";
    }

    @Override
    public String toFile() {
        String byText = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (isDone) {
            return "D|1|" + description + "|" + byText;
        } else {
            return "D|0|" + description + "|" + byText;
        }
    }
}
