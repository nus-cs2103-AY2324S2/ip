import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate deadlineFrom;
    protected LocalDate deadlineTo;
    String DATE_FORMAT = "MMM d yyyy";



    public Event(String description, String deadlineFrom, String deadlineTo) {
        super(description);
        this.deadlineFrom = LocalDate.parse(deadlineFrom);
        this.deadlineTo = LocalDate.parse(deadlineTo);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + deadlineFrom.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                + " to: " + deadlineTo.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }
}
