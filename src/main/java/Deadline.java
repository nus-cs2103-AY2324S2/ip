import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate details;


    public Deadline(String description, String by) {
        super(description);
        this.details = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + details.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
