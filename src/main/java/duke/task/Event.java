package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructs a new Event object with the specified description, start time and end time.
     *
     * @param description a String representing the description of the event task
     * @param start a LocalDateTime representing the start of the task
     * @param end a LocalDateTime representing the end of the task
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super (description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(stringFormat) + " to: " + end.format(stringFormat) + ")";
    }

    @Override
    public String getData() {
        return "E | " + super.getData() + " | " + start.format(dataFormat) + " | " + end.format(dataFormat);
    }
}
