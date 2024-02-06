package tasks.taskType;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String task;
    private String type;
    private boolean isDone;

    public Task(String task, String type, boolean isDone) {
        this.task = task;
        this.type = type;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type;
    }

    public String getTask() {
        return this.task;
    }

    public DateTimeFormatter[] getFormatsDateTime() {
        return new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        };
    }

    public String formatDates(String date) {
        String result = "";
        LocalDateTime time = null;
        DateTimeFormatter[] formats = this.getFormatsDateTime();
        for (DateTimeFormatter format : formats) {
            try {
                time = LocalDateTime.parse(date, format);
            } catch (DateTimeParseException err) {
            }
        }

        try {
            result = time.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
            System.out.println("Hello");
        } catch (DateTimeException err) {
            if (result.equals("")) {
                try {
                    result = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                } catch (DateTimeException error) {}
            }
        } catch (NullPointerException err) {
            System.out.println("Please enter the date in mm/dd/yyyy format");
        }

        return result;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + (this.isDone ? "X" : "") + "] ";
    }
}
