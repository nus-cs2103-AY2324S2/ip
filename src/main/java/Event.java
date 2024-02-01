import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    String start;
    String end;
    LocalDate startDate;
    LocalDate endDate;
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;

        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }
    }

    public Event(String task, String start, String end, boolean done) {
        super(task, done);
        this.start = start;
        this.end = end;

        try {
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }
    }

    @Override
    public String printTask() {
        String startString;
        String endString;
        if (startDate != null) {
            startString = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            startString = this.start;
        }

        if (endDate != null) {
            endString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            endString = this.start;
        }
        return "[E]" + super.printTask() + " (from: " + startString + " to: " + endString + ")";
    }

    @Override
    public String toString() {
        int marked = this.done ? 1 : 0;
        return "E | " + marked + " | " + this.task + " | " + this.start + " | " + this.end;
    }
}
