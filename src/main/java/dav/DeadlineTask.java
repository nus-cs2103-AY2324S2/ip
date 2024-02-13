package dav;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Parses task data string and returns a DeadlineTask object.
     * @param data Data string representing the task.
     * @return DeadlineTask object if parsing is successful, otherwise null.
     */
    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 4) {
            String[] dateTimeParts = parts[3].split(" ");
            if (dateTimeParts.length == 2) {
                DeadlineTask deadlineTask = new DeadlineTask(parts[2], dateTimeParts[0], dateTimeParts[1]);
                deadlineTask.isDone = parts[1].equals("1");
                return deadlineTask;
            }
        }
        return null;
    }

    /**
     * Converts the task to a string for display.
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

