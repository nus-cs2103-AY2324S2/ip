import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byWhen;

    public Deadline(String name, String byWhen) {
        super(name);
        this.byWhen = parseTime(byWhen);
    }

    private LocalDateTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(timeString, formatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + byWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
