package mona;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }
    @Override
    public String parseToLogRepresentation() {
        int completionStatus = this.isDone ? 1 : 0;
        return "E|" + completionStatus + "|" + this.description + "|" + this.start.format(formatter) + "|" + this.end.format(formatter);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(outputFormatter) + " to: " + this.end.format(outputFormatter) + ")";
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