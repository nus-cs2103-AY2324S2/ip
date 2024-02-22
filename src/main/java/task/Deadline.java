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

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MMM_DD_YYYY = "MMM dd yyyy";
    private String by;
    private LocalDate date;

    /**
     * Encapsulates a deadline task.
     * @param s The name of the deadline.
     * @param by The time at which the deadline should be completed.
     */
    public Deadline(String s, String by) {
        super(s);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
            date = LocalDate.parse(by, formatter);
            this.by = date.format(DateTimeFormatter.ofPattern(MMM_DD_YYYY));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        String taskType = "D|";
        String taskStatus = (super.getStatus() ? 1 : 0) + "|";
        String taskName = super.getTask() + "|";
        String taskDueDate = by;
        return taskType + taskStatus + taskName + taskDueDate;
    }

    @Override
    public String toString() {
        String taskType = "[D][";
        String taskStatus = (super.getStatus() ? "X" : " ") + "] ";
        String taskName = super.getTask();
        String taskDueDate = "(by: " + by + ")";
        return taskType + taskStatus + taskName + taskDueDate;
    }
}
