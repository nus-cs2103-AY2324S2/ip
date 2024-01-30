package headcube;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadlines extends Task {
    protected LocalDate by;
    public Deadlines(String description, String by) {
        super(description);
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.by = LocalDate.parse(by, targetFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.by = LocalDate.parse(by, originalFormatter);
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date format. Using current date");
                this.by = LocalDate.now();
            }

        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}

