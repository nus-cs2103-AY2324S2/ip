import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        //try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
//        } catch (DateTimeParseException e) {
//            throw new DukeException(" Invalid date/time format! Please use yyyy-MM-dd HHmm. ");
//        }

    }

    @Override
    public String toString() {
        String fromDateTime = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String toDateTime = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return " [E]" + super.toString() + " (from: " + fromDateTime + " to: " + toDateTime + ")";
    }

    public String toFileString() {
        return " E | " + super.toFileString() + " | from: " + this.from + " to: " + this.to;
    }

}
