package osiris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;


    public EventTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public EventTask(String taskName, boolean isCompleted, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName, isCompleted);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getStartDateTimeStr() {
        return this.startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    public String getEndDateTimeStr() {
        return this.endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String getStringStorageRepresentation() {
        return String.format("E | %s | %s | %s", super.getStringStorageRepresentation(), this.getStartDateTimeStr(), this.getEndDateTimeStr());
    }


    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.getStartDateTimeStr(), this.getEndDateTimeStr());
    }
}
