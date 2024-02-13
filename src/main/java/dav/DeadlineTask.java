package dav;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DeadlineTask extends Task {

    protected LocalDateTime byDateTime;

    public DeadlineTask(String description, String date, String time) {
        super(description);
        this.byDateTime = parseDateTime(date, time);
    }

    private LocalDateTime parseDateTime(String date, String time) {
        String dateTimeString = date + " " + time;
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

