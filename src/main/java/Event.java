import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;
    public Event(String task, String start, String end) {
        super(task);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public Event(String task, String start, String end, boolean status) {
        super(task, status);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String stringify() {
        String formattedStartDate = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEndDate = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.stringify() + "(from: " + formattedStartDate + "to: " + formattedEndDate + ")";
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + start + " | " + end;
    }

}
