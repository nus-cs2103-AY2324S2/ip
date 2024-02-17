package dave.tasks;

import dave.utils.DateTimeFormat;

import java.time.LocalDateTime;

public class Event extends Task {
    /** Time when event starts. */
    protected LocalDateTime from;
    /** Time when event ends. */
    protected LocalDateTime to;

    /**
     * Creates new Event object.
     * 
     * @param desc Name or description of Event object.
     * @param from Time when event starts.
     * @param to   Time when event ends.
     */
    public Event(String desc, String fromInput, String toInput) {
        super(desc);
        from = LocalDateTime.parse(fromInput, DateTimeFormat.FORMATTER_INPUT);
        to = LocalDateTime.parse(toInput, DateTimeFormat.FORMATTER_INPUT);
    }    

    /**
     * Formats the printing of the Event object when shown to user.
     * 
     * @return Printing of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[Event]%s (from: %s to: %s)", super.toString(), from.format(DateTimeFormat.FORMATTER_OUTPUT), to.format(DateTimeFormat.FORMATTER_OUTPUT));
    }

    /**
     * Formats the output of the Event object in output file.
     * 
     * @return The output to be written in the output file.
     */
    @Override
    public String fileString() {
        return String.format("EVENT | %s | %s | %s", super.fileString(), from.format(DateTimeFormat.FORMATTER_INPUT), to.format(DateTimeFormat.FORMATTER_INPUT));
    }

}
