package univus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Univus application.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructs a new instance of the Deadline class with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description.replaceFirst("deadline ", ""));
        String time = by.replaceFirst("by ", "");
        DateTimeFormatter formatter;
        if (time.length() == 11) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        this.date = LocalDate.parse(time, formatter);
    }

    /**
     * Returns a string representation of the Deadline task for display.
     *
     * @return A string containing the formatted representation of the deadline task.
     */
    @Override
    public String toString() {
        String statement = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDateTime = date.format(dateTimeFormatter);
        statement = "[D]" + super.toString() + "(by:" + formattedDateTime + ")";
        return statement;
    }
}
