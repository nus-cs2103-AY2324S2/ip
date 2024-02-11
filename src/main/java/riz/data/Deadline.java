package riz.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline consists of a task with a due date.
 * the field by represents the date and time of which the task is due by.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline (String deadline, String by) {
        super(deadline);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    /**
     * String representation of a Deadline Task.
     * @return the String includes a 'D' to indicate a "Deadline" task.
     * The due date and time is also represented in a dd MMM yyyy hh:mm 12-hour format.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "D" + super.toString() + " | " + this.by.format(outputFormatter);
    }
}
