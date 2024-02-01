package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String startToEnd;
    private LocalDate start;
    private LocalDate end;

    Event(String description, String startToEnd, String input, LocalDate start, LocalDate end) {
        super(description, input);
        this.start = start;
        this.end = end;
        this.startToEnd = formatStartToEnd();
    }

    private String formatStartToEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return this.start.format(formatter) + " to: " + this.end.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startToEnd + ")";
    }

    @Override
    public void mark() {
        super.setComplete();
        System.out.println("\tNice! I've marked this task as done:\n\t" + this.toString());
    }

    @Override
    public void unmark() {
        this.setIncomplete();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.toString());
    }

    @Override
    public String toFileFormat() {
        return String.format("Event | %s | %s", super.statusNumber, super.input);
    }
}