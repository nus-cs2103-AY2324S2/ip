import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        LocalDateTime d = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String printd = d.format(f);
        return "[D]" + super.toString() + " (by: " + printd + ")";
    }

    @Override
    public String writeToFileString() {
        return "deadline" + this.description + " /by " + this.by;
    }

}
