import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, String startDate, String endDate){
        super.description = description;
        this.startDate = LocalDateTime.parse(startDate);
        this.endDate = LocalDateTime.parse(endDate);
    }

    public Event(String description, String startDate, String endDate, boolean isDone){
        super.description = description;
        super.isDone = isDone;
        this.startDate = LocalDateTime.parse(startDate);
        this.endDate = LocalDateTime.parse(endDate);
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toWrite(){
        return "E | " + super.toWrite()
                + " | " + this.startDate
                + " | " + this.endDate;
    }

    @Override
    public String toString(){
        return this.description
                + " (from: " + super.dateTimeFormat(this.startDate)
                + " to: " + super.dateTimeFormat(this.endDate) + ")";
    }
}
