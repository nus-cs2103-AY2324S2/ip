package tasks;

import utilities.DateTime;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String DEADLINE_ICON = "D";
    private LocalDateTime by;

    Deadline(String description, LocalDateTime by) {  // default access modifier
        super(description);
        this.by = by;
    }
    
    Deadline(String description, boolean isDone, LocalDateTime by) {  // default access modifier
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return DEADLINE_ICON;
    }
    
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + DateTime.displayDateTimeToUser(by) + ")";
    }
    
    public LocalDateTime getDueDate() {
        return by;
    }
}
