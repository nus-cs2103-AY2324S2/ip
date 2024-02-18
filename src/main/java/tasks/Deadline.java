package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import parsing.DateTimeParser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime byWhen;
    protected DateTimeParser dateTimeParser;

    /**
     * Constructs a Deadline object with the specified name and deadline.
     *
     * @param name the name of the task
     * @param byWhenString the string representing the deadline date and time
     */
    public Deadline(String name, String byWhenString) {
        super(name);
        this.dateTimeParser = new DateTimeParser();
        this.byWhen = this.dateTimeParser.parseDateTime(byWhenString);
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string containing task type, name, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Returns a string formatted for writing to file.
     *
     * @return a string containing task type, name, and deadline in a specific format
     */
    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + byWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
