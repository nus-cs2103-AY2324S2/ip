package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, Boolean isDone,String from, String to) throws DateTimeParseException{
        super(description, isDone);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse the date/time: " + from);
            throw e;
        }
        try {
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse the date/time: " + to);
            throw e;
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " +  this.isDone + " | " + this.description  + " | " + this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + "-" + this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
