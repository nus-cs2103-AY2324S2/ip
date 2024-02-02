import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
* Deadline class
*/
public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byString;
    protected DateTimeFormatter f1 = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
    protected DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
    protected DateTimeFormatter f3 = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
    protected DateTimeFormatter f4 = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.ENGLISH);

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        try {
            this.byDate = parseDate(by, f1, f2, f3, f4); // Parse the by string
        } catch (DateTimeException e) {
            // Handle the exception or rethrow it with a custom message
            throw new DateTimeException("Error parsing date: " + e.getMessage());
        }
    }

    // Parse the date string and return a LocalDate object
    private LocalDate parseDate(String by, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(by, formatter);
            } catch (Exception e) {
                // Try the next format
            }
        }
        // If none of the formats match, you may want to handle this case
        throw new IllegalArgumentException("Date could not be parsed with any of the provided formats");
    }

    @Override
    protected String taskType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return super.toString() + " (by: " + byDate.format(displayFormatter) + ")";
    }
}