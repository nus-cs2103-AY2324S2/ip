import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate from;
    private LocalDate by;
    public Event(String description, String from, String by) {
        super(description);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Event(String description, String from, String by, boolean isDone) {
        super(description, isDone);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    @Override
    public String show() {
        super.status = isDone? "X": " ";
        String fromFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String byFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String fromByFormat = "(from: " + fromFormat + " to: " + byFormat + ")";
        return "[E]" + "[" + status + "]" + " " + this.description + " " + fromByFormat;
    }
}
