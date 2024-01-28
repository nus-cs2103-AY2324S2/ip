import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime date;

    public Deadline(String task, String date) {
        super(task);
        this.date = setDate(date);
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
        return "[D]" + super.printTask() + " (by: " + printDate() + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(), this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    public String printDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return this.date.format(formatter);
    }
}
