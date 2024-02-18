package cleo;

import java.time.LocalDateTime;;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use 'd/M/yyyy HHmm', e.g., '2/12/2019 1800'.");
        }
    }
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return super.toString() + " (by: " + this.getBy().format(formatter) + ")";
    }
}
