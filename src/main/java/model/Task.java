package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public LocalDateTime parseDateTime(String input) {
        DateTimeFormatter[] formattersWithTime = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
        };

        DateTimeFormatter[] formattersWithoutTime = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        };

        for (DateTimeFormatter formatter : formattersWithTime) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        for (DateTimeFormatter formatter : formattersWithoutTime) {
            try {
                return LocalDate.parse(input, formatter).atTime(0, 0);
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        throw new DateTimeParseException("Invalid date/time format", input, 0);
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + (this.tag == null ? "" : " #" + this.tag);
    }

    public abstract String fileSavingString();

    public String getTag() {
        if (this.tag == null) {
            return "";
        } else {
            return this.tag;
        }
    }
}
