package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String name, String date) throws InvalidDateFormat {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    @Override
    public String toString() {
        String date = String.format(" (by: %s)", this.date.format(FORMATTER));
        return "[D]" + super.toString() + date;
    }
}