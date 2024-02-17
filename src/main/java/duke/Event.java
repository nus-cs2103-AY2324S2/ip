package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start time, and end time.
 * It extends the Task class by adding time-specific information.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected Tag tag;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.tag = null;
    }

    /**
     * Returns a string representation of the event task, including its type, status, description, and timing.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + super.description + " (from: " + from + " to: " + to + ")"
                + (tag == null ? "" : " " + tag.getTagName());
    }

    /**
     * Returns a string representation of the event task formatted for file storage.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " - " + to
                + (tag == null ? "" : " | " + tag.getTagName());
    }

    /**
     * Creates an Event object from a string representation formatted for file storage.
     *
     * @param str The string representation of the event from a file.
     * @return An Event object, or null if the string format is invalid.
     */
    public static Event fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("E")) {
            return null;
        }
        String description = parts[2].trim();
        String[] times = parts[3].split(" - ");
        if (times.length < 2) {
            return null;
        }
        String from = times[0].trim();
        String to = times[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
        boolean isDone = parts[1].trim().equals("1");
        Event event = new Event(description, dateTimeFrom, dateTimeTo);
        if (isDone) {
            event.markAsDone();
        }
        if (parts.length > 4) {
            event.tag = new Tag(parts[4].trim());
        }
        return event;
    }

    /**
     * Returns the start time of the event.
     *
     * @return A string representation of the start time.
     */
    public String getFromDate() {
        return from.toString();
    }

    /**
     * Returns the end time of the event.
     *
     * @return A string representation of the end time.
     */
    public String getToDate() {
        return to.toString();
    }

    public void addTag(String tag) {
        this.tag = new Tag(tag);
    }

    public void removeTag() {
        this.tag = null;
    }

    public Tag getTag() {
        return tag;
    }
}
