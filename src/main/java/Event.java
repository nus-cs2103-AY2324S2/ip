import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    public Event(String description, LocalDate startDate, LocalTime startTime,
                 LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDate + " " + startTime
                + " | to: " + endDate + " " + endTime + ")";
    }
}
