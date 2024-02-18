package bmo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Events(String task, LocalDateTime start, LocalDateTime end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy', 'h:mma"));
    }

    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy', 'h:mma"));
    }

    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        String startStr = this.start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        String endStr = this.end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

        return "E | " + done + " | " + super.toString() + " | "
                + startStr + " | " + endStr + "\n";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }
}
