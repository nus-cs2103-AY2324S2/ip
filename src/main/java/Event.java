import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;


    public Event (String description, LocalDateTime newStartDate, LocalDateTime newEndDate) {
        super(description);
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }

    public Event (String description, String newStartDate, String newEndDate) {
        super(description);
        this.startDate = LocalDateTime.parse(newStartDate);
        this.endDate = LocalDateTime.parse(newEndDate);
    }

    public String formattedStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern(this.dateTimeFormat));
    }

    public String formattedEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern(this.dateTimeFormat));
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.formattedStartDate(), this.formattedEndDate());
    }

    @Override
    public String toFileString() {
        return String.format("E,%s,%s,%s", super.toFileString(), this.startDate, this.endDate);
    }
}
