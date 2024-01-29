import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String endDate) {
        super(description);
        this.date = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "]" + " Deadline | "
                + this.description + " | "
                + this.date;
    }
}