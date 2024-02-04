import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    String DATE_FORMAT = "MMM d yyyy";
    String DIVIDER = " | ";


    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    @Override
    public String getFileString() {
        return "D" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description + DIVIDER + by;
    }

}
