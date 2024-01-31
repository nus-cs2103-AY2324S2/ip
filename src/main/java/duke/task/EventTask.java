package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public EventTask(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EventTask(String description, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        LocalDate tempStartTime = startTime.plusDays(0);
        LocalDate tempEndTime = endTime.plusDays(0);
        return "[E]" + super.toString() + " (from: " + tempStartTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + tempEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
