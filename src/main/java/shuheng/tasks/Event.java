package shuheng.tasks;

import java.time.LocalDateTime;

/**
 * This class represents a event task.
 */
public class Event extends Task {

    private LocalDateTime start;

    private LocalDateTime end;

    /**
     * Constructor for events.
     * @param description The description of the event.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, PriorityLevel priority) {
        super(description, "[E]", priority);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String getLogRepresentation() {
        String completeStatus = "F";
        if (this.isDone) {
            completeStatus = "T";
        }
        return "E" + "," + completeStatus + ","
            + this.description + "," + this.getPriority() + ","
            + this.getStart().toString() + "," + this.getEnd().toString();
    }

    @Override
    public String getTimeData() {
        return "(from: " + this.decomposeDateTime(this.start)
            + " to: " + this.decomposeDateTime(this.end) + ")";
    }
}
