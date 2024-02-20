package panda.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event with the given description, start time, and end time.
     * 
     * @param desc the description of the event.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("MMM d'th' ha"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"));
        DateTimeFormatter formatter = builder.toFormatter(Locale.US);
        if (!from.toLowerCase().contains("am") && !from.toLowerCase().contains("pm")) {
            from += " 12am"; // append default time if none is provided
        }
        if (!to.toLowerCase().contains("am") && !to.toLowerCase().contains("pm")) {
            to += " 12am"; // append default time if none is provided
        }
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Returns the formatted string representation of the start time of the event.
     * 
     * @return the formatted string representation of the start time of the event.
     */
    private String startString() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
    }

    /**
     * Returns the formatted string representation of the end time of the event.
     * 
     * @return the formatted string representation of the end time of the event.
     */
    private String stopString() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
    }

    /**
     * Returns the string representation of the event.
     * 
     * @return the string representation of the event.
     */
    public String toString() {
        String from = startString();
        String to = stopString();
        if (from.length() >= 12 && to.length() >= 12 && from.substring(0, 12).equals(to.substring(0, 12))) {
            to = to.substring(12);
        }
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string representation of the event suitable for saving to a file.
     * 
     * @return the string representation of the event.
     */
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + startString() + " | " + stopString();
    }
}
