import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException{
        super(description);

        try {
            DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(by, input);
            DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            this.by = date.format(output);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time Format. Please use yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.by;
    }
}
