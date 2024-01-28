import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = setDate(start);
        this.end = setDate(end);
    }

    public LocalDateTime setDate(String s) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("OOPS!!! Invalid start/end date ૮ ̷ ̷ ̷・ﻌ ̷ ̷・ ა Specify date in format 'dd-MM-yyyy HH:mm'.", e.getParsedString(), e.getErrorIndex());
        }
        return date;
    }
    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + printDate(this.start) + " to: " + printDate(this.end) + ")";
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s | %s", super.toString(), this.start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), this.end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    public String printDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return date.format(formatter);
    }
}
