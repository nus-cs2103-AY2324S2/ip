package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime byDate;

    public Deadline(String description, String byDate) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.byDate = LocalDateTime.parse(byDate, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time in the format yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D | %s | %s", fileLine.substring(4), this.byDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), this.byDate.format(formatter));
    }
}