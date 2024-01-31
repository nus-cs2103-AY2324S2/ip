package toothless.tasks;

import java.time.LocalDateTime;
import toothless.ToothlessException;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, String startDate, String endDate) throws ToothlessException {
        super.description = description;
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        if (start.isAfter(end)) {
            throw new ToothlessException("End date is earlier :/");
        }
        this.startDate = start;
        this.endDate = end;
    }

    public Event(String description, String startDate, String endDate, boolean isDone) throws ToothlessException{
        super.description = description;
        super.isDone = isDone;
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        if (start.isAfter(end)) {
            throw new ToothlessException("End date is earlier :/");
        }
        this.startDate = start;
        this.endDate = end;
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
