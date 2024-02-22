package dav;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Set;

/**
 * Represents a task that is an event with a specified start and end date/time.
 */
class EventTask extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Constructs an EventTask with the specified description and date/time.
     * @param description Description of the event task.
     * @param fromDateTime Start date/time of the event.
     * @param toDateTime End date/time of the event.
     */
    public EventTask(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Constructs an EventTask with the specified description and date/time strings.
     * @param description Description of the event task.
     * @param from Start date/time of the event in string format.
     * @param to End date/time of the event in string format.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    /**
     * Parses task data string and returns an EventTask object.
     * @param data Data string representing the task.
     * @return EventTask object if parsing is successful, otherwise null.
     */
    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");

        if (parts.length < 5) {
            return null;
        }

        EventTask eventTask = new EventTask(parts[2], parts[3], parts[4]);
        eventTask.isDone = parts[1].equals("1");

        if (parts.length > 5) {
            String[] tagParts = parts[5].split(", ");
            for (String tag : tagParts) {
                eventTask.addTag(tag);
            }
        }

        return eventTask;
    }


    /**
     * Parses a date-time string and returns a LocalDateTime object.
     *
     * @param dateTime The string representing the date and time in "yyyy-MM-dd HHmm" format.
     * @return A LocalDateTime object representing the parsed date and time,
     *         or null if parsing is unsuccessful.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Converts the task to a string for data storage.
     * @return Formatted string for data storage.
     */
    @Override
    public String toDataString() {
        StringBuilder dataString = new StringBuilder("E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " | " + toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        Set<String> eventTags = getTags();
        if (!eventTags.isEmpty()) {
            dataString.append(" | ");
            for (String tag : eventTags) {
                dataString.append(tag).append(", ");
            }
            dataString.delete(dataString.length() - 2, dataString.length());
        }

        return dataString.toString();
    }

    /**
     * Converts the task to a string for display.
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n"
                + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

