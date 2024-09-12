package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String name;
    protected LocalDate deadline;

    /**
     * Constructor for a task list object.
     *
     * @param name The task name.
     * @param date The deadline.
     */
    public Deadline(String name, LocalDate date) {
        super();
        this.name = name;
        this.deadline = date;
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of the task.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String description, boolean isDone) {
        super(isDone);
        int idx = description.indexOf("(by: ");
        this.name = description.substring(0, idx - 1);
        String date = description.substring(idx + 5, description.length() - 1);
        this.deadline = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean isMatchKeyword(String keyword) {
        return this.name.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + "[D] " + this.name
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
