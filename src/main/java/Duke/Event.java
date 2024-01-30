package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String typeid() {
        return "E";
    }

    public String timeprint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startstring = formatter.format(start);
        String endstring = formatter.format(end);
        return ("~" + startstring+"~" + endstring);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}