package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim(); // Trim leading and trailing whitespace
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        //return "[D]" + super.toString() + "(by:" + getBy() + ")";
        return "[D]" + super.toString()
                + "(by:" + getDate()
                .format(DateTimeFormatter
                        .ofPattern("MMM dd yyyy"))
                + ")";
    }

    public String getBy() {
        return by;
    }

    public LocalDate getDate() {
        return date;
    }
}
