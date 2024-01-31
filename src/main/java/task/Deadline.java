package task;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a subclass of task.Task that stores a task with a completion date.
 * @author Koo Zhuo Hui
 */
public class Deadline extends Task {
    
    private String by;
    private LocalDate date;
    /**
     *
     * @param s The name of the deadline.
     * @param by The time at which the deadline should be completed.
     */
    public Deadline(String s, String by) {
        super(s);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(by, formatter);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    @Override
    public String encode() {
        String s = "D|" + (super.getStatus() ? 1 : 0) + "|" + super.getTask() + "|" + by;
        return s;
    }
    @Override
    public String toString() {
        return "[D][" + (super.getStatus() ? "X" : " ") + "] " +
                super.getTask() + "(by: " + by + ")";

    }
}
