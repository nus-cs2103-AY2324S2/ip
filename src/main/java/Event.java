import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate from;
    LocalDate to;
    public Event(String name, String from, String to) {
        super(name);
        try {
            LocalDate fromDate = LocalDate.parse(from);
            this.from = fromDate;
            LocalDate toDate = LocalDate.parse(to);
        } catch (DateTimeException e) {
            System.out.println("Error in date formats D: It should follow yyyy-mm-dd");
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
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("%s|%s|%s|%s\n", "E", super.dataString(), formattedFrom, formattedTo);
    }
}
