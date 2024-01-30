import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String taskName, String ddl) {
        super(taskName);
        this.by = parseDeadline(ddl);
    }

    private LocalDate parseDeadline(String ddl) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(ddl, f);
    }

    public LocalDate getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        String formattedDate = this.by.getDayOfMonth() + " " + this.by.getMonth().toString().substring(0, 3) + " "
                + this.by.getYear();
        return "[D]" + super.toString() + " ( by: " + formattedDate + ")";
    }
}
