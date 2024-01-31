import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws DukeException{
        super(description);
        try {
            DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date1 = LocalDateTime.parse(from, input);
            LocalDateTime end1 = LocalDateTime.parse(to, input);
            DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            this.from = date1.format(output);
            this.to = end1.format(output);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Invalid time Format. Please use yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.from + "-" + this.to;
    }
}
