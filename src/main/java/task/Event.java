package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is a child of class Task.
 * Event handles the task where there is date from and date to.
 */

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    /**
     * This function returns a String type representation of date.
     * 
     * @param localDate
     * @return String of a date.
     */

    private String formatter(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = localDate.format(formatter);
        return formattedDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                formatter(from) + " to: " + formatter(to) + ")";
    }
}