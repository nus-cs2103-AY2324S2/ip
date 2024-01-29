import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadlines extends Task {
    protected LocalDate by;
    public Deadlines(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Using current date");
            this.by = LocalDate.now();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + "(by: " + by + ")";
    }
}

