package dibo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        String date = by;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        this.by = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getSaveFormat() {
        return "deadline | " + (this.isDone ? "1 " : "0 | ") + this.description
                + " | " + this.by;
    }
}
