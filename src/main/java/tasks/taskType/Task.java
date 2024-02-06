package tasks.taskType;

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
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
        };
    }

    public String formatDates(String date) {
        String result = "";
        DateTimeFormatter[] formats = this.getFormatsDateTime();
        for (DateTimeFormatter format : formats) {
            try {
                LocalDateTime time = LocalDateTime.parse(date, format);
                result = time.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
                break;
            } catch (DateTimeParseException error) {
                break;
            } finally {
                System.out.println("Please pass date in dd/MM/yyyy HHmm format");
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + (this.isDone ? "X" : "") + "] ";
    }
}
