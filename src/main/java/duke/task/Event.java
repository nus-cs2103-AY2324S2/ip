//public class Event extends Task {
//    String start;
//    String end;
//
//    public Event(String description, String start, String end) {
//        super(description);
//        this.start = start;
//        this.end = end;
//    }
//
//    @Override
//    public String toString() {
//        return "[E]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (from: " + this.start + " to: " + this.end + ")";
//    }
//
//    public String type() {
//        return "E";
//    }
//
//    public String getDate() {
//        return this.start + "-" + this.end;
//    }
//}
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Contains the details of events as a part of task.
 */

public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs the Events Object.
     * @param start Start date and time of the event.
     * @param end end date and time of the event.
     * @param description Contains the description of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + (this.checkDone() ? "[X] " : "[ ] ") + this.getDescription() + " (from: " + this.start.format(formatter) + " to: " + this.end.format(formatter) + ")";
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return this.start.format(formatter) + "-" + this.end.format(formatter);
    }

    public LocalDate getRawDate() {
        return this.start.toLocalDate();
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getStart() {
        return start;
    }
}
