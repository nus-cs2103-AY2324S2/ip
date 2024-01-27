package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String startDateString;
    private String endDateString;

    public Event(String description, boolean hasDone, LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString = startDate.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        this.endDateString = endDate.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStartDateString() {
        return startDateString;
    }


    public String getEndDateString() {
        return endDateString;
    }


    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.startDateString + " to: " + this.endDateString + ")";
    }
}
