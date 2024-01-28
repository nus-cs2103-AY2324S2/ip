import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    static final DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String name, String date) throws InvalidDateFormat {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    @Override
    public String toString() {
        String date = String.format(" (by: %s)", this.date.format(f));
        return "[D]" + super.toString() + date;
    }
}
