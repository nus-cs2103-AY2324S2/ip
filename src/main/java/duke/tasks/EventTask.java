package duke.tasks;

import java.time.LocalDateTime;

public class EventTask extends Task{
    private LocalDateTime start;
    private LocalDateTime end;
    public EventTask(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public EventTask(String desc, String isDone, LocalDateTime start, LocalDateTime end) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }
    public String getStatusIcon() {
        return (this.isDone() ? "[E][X] " : "[E][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (from: " + Task.toStringDateTime(this.start) +
                " to: " + Task.toStringDateTime(this.end) + ")";
    }
    public String save() {
        String isDone = this.isDone() ? "1" : "0";
        return "E," + isDone + "," + this.getDesc() + "," + this.start + "," + this.end;
    }
}
