import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    private String formatter(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = localDate.format(formatter);
        return formattedDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter(by) + ")";
    }
}
