package cleo;

import java.time.LocalDate;
;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    Event(String description, String from, String to) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            this.from = LocalDate.parse(from, formatter);
            this.to = LocalDate.parse(to, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use 'd/M/yyyy', e.g., '2/12/2019'.");
        }
    }
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    public LocalDate getFrom() {
        return this.from;
    }
    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (from " + this.getFrom().format(formatter) + " to " + this.getTo().format(formatter) + ")";
    }


}
