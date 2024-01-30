package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Event(String title, LocalDateTime startTime, LocalDateTime endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String save() {
        if (this.marked) {
            return "E | Done | " + this.title;
        } else {
            return "E | Not Done | " + this.title;
        }
    }

    @Override
    public String toString() {
        if (this.marked) {
            return "[E][X] " + this.title + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[E][ ] " + this.title + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

}
