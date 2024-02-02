import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate){
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate +")";
    }
}
