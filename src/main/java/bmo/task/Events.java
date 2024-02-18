package bmo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event.
 */
public class Events extends Task{

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for the Events class.
     *
     * @param task The task to be done.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
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

    /**
     * Returns the formatted string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        String startStr = this.start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        String endStr = this.end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

        return "E | " + done + " | " + super.toString() + " | "
                + startStr + " | " + endStr +"\n";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }
}
