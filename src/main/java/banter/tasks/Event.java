package banter.tasks;

import java.time.LocalDateTime;
import banter.utilities.DateTime;

public class Event extends banter.tasks.Task {
    public static final String EVENT_ICON = "E";
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String description, LocalDateTime from, LocalDateTime to) {  // default access modifier
        super(description);
        this.from = from;
        this.to = to;
    }
    
    Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {  // default access modifier
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return EVENT_ICON;
    }
    
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (from: " + DateTime.displayDateTimeToUser(from) + 
                " to: " + DateTime.displayDateTimeToUser(to) + ")";
    }

    public LocalDateTime getStart() {
        return from;
    }
    
    public LocalDateTime getEnd() {
        return to;
    }
}
