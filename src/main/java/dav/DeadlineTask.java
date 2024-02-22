package dav;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Set;

/**
 * Represents a task with a deadline.
 */
class DeadlineTask extends Task {

    protected LocalDateTime byDateTime;

    /**
     * Constructor for DeadlineTask class.
     * @param description Description of the task.
     * @param date Date of the deadline.
     * @param time Time of the deadline.
     */
    public DeadlineTask(String description, String date, String time) {
        super(description);
        this.byDateTime = parseDateTime(date, time);
    }

    /**
     * Parses task data string and returns a DeadlineTask object.
     * @param data Data string representing the task.
     * @return DeadlineTask object if parsing is successful, otherwise null.
     */
    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");

        if (parts.length < 4) {
            return null;
        }

        String description = parts[2];
        String dateTimeString = parts[3];
        String[] dateTimeParts = dateTimeString.split(" ");

        String date = dateTimeParts[0];
        String time = dateTimeParts[1];

        DeadlineTask deadlineTask = new DeadlineTask(description, date, time);
        deadlineTask.isDone = parts[1].equals("1");

        if (parts.length > 4) {
            String[] tagParts = parts[4].split(", ");
            for (String tag : tagParts) {
                deadlineTask.addTag(tag);
            }
        }

        return deadlineTask;
    }

    /**
     * Parses date and time strings and returns a LocalDateTime object.
     *
     * @param date The string representing the date in "yyyy-MM-dd" format.
     * @param time The string representing the time in "HHmm" format.
     * @return A LocalDateTime object representing the combined date and time,
     *         or null if parsing is unsuccessful.
     */
    private LocalDateTime parseDateTime(String date, String time) {
        String dateTimeString = date + " " + time;
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Converts the task to a string for data storage.
     * @return Formatted string for data storage.
     */
    @Override
    public String toDataString() {
        StringBuilder dataString = new StringBuilder("D | " + (isDone ? "1" : "0") + " | " + description
                + " | " + byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        Set<String> deadlineTags = getTags();
        if (!deadlineTags.isEmpty()) {
            dataString.append(" | ");
            for (String tag : deadlineTags) {
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
        return "[D]" + super.toString() + "\n"
                + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

