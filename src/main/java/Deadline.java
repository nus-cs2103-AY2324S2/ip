import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected DateTask by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime parsedDateTimeBy = LocalDateTime.parse(by, formatter);
        this.by = new DateTask(parsedDateTimeBy);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s",
         "[D]",
         super.toString(),
         " (by: " + this.by + ")");
    }
    
}
