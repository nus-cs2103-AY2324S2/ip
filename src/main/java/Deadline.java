import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    public Deadline(String description, String by) throws DukeException{
        super(description);

        try {
            LocalDateTime date = LocalDateTime.parse(by, input);
            this.by = date.format(output);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format. Please use yyyy-MM-dd HH:mm.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + LocalDateTime.parse(by, output).format(input);
    }
}
