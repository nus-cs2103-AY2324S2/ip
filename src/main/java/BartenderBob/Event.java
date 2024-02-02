package BartenderBob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate from;
    private LocalDate by;
    public Event(String description, String from, String by) {
        super(description);
        if (!isValidDateFormat(from, by)) {
            throw new IllegalArgumentException();
        }
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Event(String description, String from, String by, boolean isDone) {
        super(description, isDone);
        if (!isValidDateFormat(from, by)) {
            throw new IllegalArgumentException();
        }
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    private boolean isValidDateFormat(String from, String by) {
        try {
            LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
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
