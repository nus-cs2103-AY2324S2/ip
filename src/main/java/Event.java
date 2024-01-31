import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate from;
    LocalDate to;
    public Event(String name, String from, String to) throws InvalidCommandException {
        super(name);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeException e) {
            throw new InvalidCommandException(
                    "Invalid input format for date :( Please use the format yyyy-mm-dd instead!");
        }
    }

    @Override
    public String toString() {
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("%s %s (from: %s to: %s)", "[E]", super.toString(), formattedFrom, formattedTo);
    }

    @Override
    public String dataString() {
        return String.format("%s|%s|%s|%s\n", "E", super.dataString(), this.from, this.to);
    }
}
