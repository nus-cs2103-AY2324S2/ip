package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {
    private LocalDate byDate;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            // Handle exception or set a default value if the date is in the wrong format
            throw new DukeException("Invalid date format (yyyy-mm-dd).");
        }
    }

    public String getBy() {
        return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getByForFile() {
        return byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}

