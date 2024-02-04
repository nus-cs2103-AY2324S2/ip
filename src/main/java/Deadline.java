import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getDataString() {
        return "D | " + (isDone? "1" : "0") + " | " + super.getDescription() +  " | " + by;
    }
}
