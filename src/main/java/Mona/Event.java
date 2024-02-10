package mona;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents event tasks which specify a start and end date
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Constructor for an event task
     * @param description the description for the event task
     * @param start the start date and time
     * @param end the end date and time
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }
    @Override
    public String parseToLogRepresentation() {
        int completionStatus = this.isDone ? 1 : 0;
        return "E|" + completionStatus + "|" + this.description + "|"
                + this.start.format(formatter) + "|" + this.end.format(formatter);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(outputFormatter)
                + " to: " + this.end.format(outputFormatter) + ")";
    }
    @Override
    public void updateDetails(String newDetails) {
        String[] subDetails = newDetails.split(" /from ");
        String[] startAndEnd = subDetails[1].split(" /to ");
        this.description = subDetails[0];
        this.start = LocalDateTime.parse(startAndEnd[0], formatter);
        this.end = LocalDateTime.parse(startAndEnd[1], formatter);
    }
}
