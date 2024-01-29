import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements Serializable {

    protected LocalDateTime by;
    protected DayOfWeek dayOfWeek;

    public Deadline(String description, String by) {
        super(description);
        parseDeadline(by.trim());
    }

    private void parseDeadline(String by) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.by = LocalDateTime.parse(by, dateTimeFormatter);

        } catch (DateTimeParseException e) {

            try {
                // If parsing as specific date/time format fails, try parsing as day of the week
                DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE");
                this.dayOfWeek = DayOfWeek.from(dayOfWeekFormatter.parse(by));

            } catch (DateTimeParseException error) {
                System.err.println("Error: Invalid date/time input. Please delete the task and provide a valid date/time or day of the week again.");
            }
        }
    }

    @Override
    public String toString() {

        if (by != null) {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + dayOfWeek + ")";
        }
    }
}
