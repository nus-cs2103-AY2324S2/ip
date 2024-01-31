import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    protected LocalDate startTime;
    protected LocalDate endTime;
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);
        return "E |" + super.toString().substring(1) + "| " + formattedStartTime + " to " + formattedEndTime;
        //return "E |" + super.toString().substring(1) + "| " + getExactTime();
    }



}
