import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDate(by);
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("Unable to parse the date: " + dateString);
            return null;
        }
    }

    private String formatDate(LocalDate date) {
        if (date == null) {
            return "no date";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return "D | " + super.toFileFormat() + " | " + (by != null ? by.format(formatter) : "");
    }


}
