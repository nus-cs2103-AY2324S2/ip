package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }
    
    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + 
                this.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                this.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")) + ")";
    }
}
