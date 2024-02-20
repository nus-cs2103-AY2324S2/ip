package chingu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is implementation of Task which contains description and by.
 */
public class Deadline extends Task {
    public LocalDate by;

    /**
     * Return an instance of the class Deadline with description of the Task
     * and deadline date by.
     * @param description that describe what the task is about.
     * @param by that is deadline date in form of yyyy/MM/dd
     */
    public Deadline(String description, String by, String priority) {
        super(description, priority);
        this.by = LocalDate.parse(by,DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    /**
     * Returns String of task detail with type of Task (Deadline),
     * deadline date (by) - in form of MMM d yyyy -, description.
     * @return Task detail
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
