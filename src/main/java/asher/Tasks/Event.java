package asher.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;

    public Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String writeToString() {
        String status = isDone ? "1" : "0";
        return "E" + " | " + status + " | " + description + " | " + startDate + " | " + startTime + " | " + endDate + " | " + endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formattingDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formattingTime = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedStartDate = startDate.format(formattingDate);
        String formattedEndDate = endDate.format(formattingDate);
        String formattedStartTime = startTime.format(formattingTime);
        String formattedEndTime = endTime.format(formattingTime);
        return " [E]" + super.toString() + " (from: " + formattedStartDate + "," + " " + formattedStartTime + " to: " + formattedEndDate + "," + " " + formattedEndTime + ")";
    }
}
