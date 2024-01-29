import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byWhen;

    public Deadline(String name, String byWhen) {
        super(name);
        this.byWhen = parseByWhen(byWhen);
    }

    private LocalDateTime parseByWhen(String byWhenString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(byWhenString, formatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + super.toWrite() + " | " + byWhen;
    }
}
