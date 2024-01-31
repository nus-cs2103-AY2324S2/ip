import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        String date1 = start;
        String date2 = end;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);
        this.start = localDate1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.end = localDate2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String getSaveFormat() {
        return "event | " + (this.isDone ? "1 " : "0 | ") + this.description
                + " | " + this.start + " | " + this.end;
    }
}
