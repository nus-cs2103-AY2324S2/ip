package hal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate deadlineFrom;
    protected LocalDate deadlineTo;
    String DATE_FORMAT = "MMM d yyyy";
    String DIVIDER = " | ";

    public Event(boolean isDone, String description, String deadlineFrom, String deadlineTo) {
        this.isDone = isDone;
        this.description = description;
        this.deadlineFrom = LocalDate.parse(deadlineFrom);
        this.deadlineTo = LocalDate.parse(deadlineTo);
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " +
                deadlineFrom.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                + " to: " + deadlineTo.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    @Override
    public String getFileString() {
        return "E" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description
                + DIVIDER + deadlineFrom + DIVIDER + deadlineTo;
    }

}
