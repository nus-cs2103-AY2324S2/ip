package dave.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Time when event starts. */
    protected LocalDateTime from;
    /** Time when event ends. */
    protected LocalDateTime to;
    /** The format of the input. */
    static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The format of the output. */
    static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy ha");

    /**
     * Creates new Event object.
     * 
     * @param desc Name or description of Event object.
     * @param from Time when event starts.
     * @param to   Time when event ends.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDateTime.parse(from, FORMATTER_INPUT);
        this.to = LocalDateTime.parse(to, FORMATTER_INPUT);
    }    

    /**
     * Formats the printing of the Event object when shown to user.
     * 
     * @return Printing of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[Event]%s (from: %s to: %s)", super.toString(), this.from.format(FORMATTER_OUTPUT), this.to.format(FORMATTER_OUTPUT));
    }

    /**
     * Formats the output of the Event object in output file.
     * 
     * @return The output to be written in the output file.
     */
    @Override
    public String fileString() {
        return String.format("EVENT | %s | %s | %s", super.fileString(), this.from.format(FORMATTER_INPUT), this.to.format(FORMATTER_INPUT));
    }

}
