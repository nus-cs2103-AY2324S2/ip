package campus.tasks;

import campus.exceptions.CampusException;

import java.time.LocalDateTime;

/**
 * Contains the logic for Event that extends the abstract class Task
 * Has both a start and end time.
 */
public class Event extends Task {
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    public Event(String eventName, String startDateTime, String endDateTime) throws CampusException {
        this.taskName = eventName;
        this.isCompleted = false;

        if (!isValidDateTimeFormat(startDateTime, this.formatter)) {
            throw new CampusException("Error! An event task must have a start datetime in the right format, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        } else if (!isValidDateTimeFormat(endDateTime, this.formatter)) {
            throw new CampusException("Error! An event task must have an end datetime in the right format, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        } else {
            this.startDateTime = LocalDateTime.parse(startDateTime, this.formatter);
            this.endDateTime = LocalDateTime.parse(endDateTime, this.formatter);
        }
    }

    public Event(String eventName, Boolean completed, String startDateTime, String endDateTime) throws CampusException {
        this.taskName = eventName;
        this.isCompleted = completed;

        if (!isValidDateTimeFormat(startDateTime, this.formatter)) {
            throw new CampusException("Error! An event task must have a start datetime in the right format, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        } else if (!isValidDateTimeFormat(endDateTime, this.formatter)) {
            throw new CampusException("Error! An event task must have an end datetime in the right format, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        } else {
            this.startDateTime = LocalDateTime.parse(startDateTime, this.formatter);
            this.endDateTime = LocalDateTime.parse(endDateTime, this.formatter);
        }
    }

    @Override
    public void markComplete() {
        this.isCompleted = true;
    }

    @Override
    public void markIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String xMarker = this.isCompleted ? "[X]" : "[ ]";
        return String.format("[E] %s %s (from: %s to: %s)", xMarker, this.taskName, this.startDateTime.format(this.formatter), this.endDateTime.format(this.formatter));
    }

    @Override
    public String toDBFormat() {
        String completed = this.isCompleted ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", completed, this.taskName, this.startDateTime.format(this.formatter), this.endDateTime.format(this.formatter));
    }
}
