package lunaris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;

    // code snippet below inspired from:
    // https://stackoverflow.com/questions/44600420/datetimeformatter-accepting-multiple-dates-and-converting-to-one-java-time-libr
    protected DateTimeFormatter parser = DateTimeFormatter
            .ofPattern("[yyyy-MM-dd HHmm][MMM dd yyyy HHmm]");

    /**
     * Constructor for Deadline task.
     *
     * @param description Description of deadline task.
     * @param by Date and Time of deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by, parser);
    }

    /**
     * Constructor for Deadline task with status. Used for file access.
     *
     * @param status isDone status of task. Either "1" or "0".
     * @param description Description of deadline task.
     * @param by Date and Time of deadline task.
     */
    public Deadline(String status, String description, String by) {
        super(status.equals("1"), description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by, parser);
    }

    public String formatDateTime() {
        return this.dateTime.format(DateTimeFormatter
                .ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description + " | " + this.formatDateTime() +
                " | " + tagName;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.formatDateTime() + ")";
    }
}