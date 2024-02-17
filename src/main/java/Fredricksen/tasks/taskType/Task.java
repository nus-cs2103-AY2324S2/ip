package Fredricksen.tasks.taskType;

import java.time.DateTimeException;
import java.time.LocalDate;
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
        LocalDate dateWoTime = null;
        DateTimeFormatter[] formats = this.getFormatsDateTime();
        for (DateTimeFormatter format : formats) {
            try {
                time = LocalDateTime.parse(date, format);
                break;
            } catch (DateTimeParseException err) {
                dateWoTime = LocalDate.parse(date, format);
                break;
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

        if (time != null) {
            try {
                result = time.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a"));
            } catch (DateTimeException err) {}
        } else {
            try {
                result = dateWoTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeException err) {}
        }
        return result;
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + (this.isDone ? "X" : "") + "] ";
    }
}
