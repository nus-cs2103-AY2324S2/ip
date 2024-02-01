package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** LocalDateTime Object of when the Event Task is stated to begin. */
    private LocalDateTime fromDate;
    /** LocalDateTime Object of when the Event Task is stated to end. */
    private LocalDateTime toDate;

    /**
     * Constructs a Event Object.
     *
     * @param description String containing the description of the Task.
     * @param fromDate LocalDateTime Object of when the Event Task is stated to begin.
     * @param toDate LocalDateTime Object of when the Event Task is stated to end.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns the Event Object as a String
     *
     * @return String Representation of the Event Object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        String fromDateString = fromDate.format(dTFormatter);
        String toDateString = toDate.format(dTFormatter);
        return String.format("[E]" + super.toString() + " (from: %s to: %s)", fromDateString, toDateString);
    }
}
