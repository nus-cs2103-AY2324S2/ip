package campus.tasks;

import campus.exceptions.CampusException;

import java.time.LocalDateTime;

/**
 * Contains logic for the Deadline class that extends the abstract Task Class
 * Only has an end time.
 */
public class Deadline extends Task {
    LocalDateTime endDateTime;

    public Deadline(String deadlineName, String endDateTime) throws CampusException {
        this.taskName = deadlineName;
        this.completed = false;

        if (!isValidDateTimeFormat(endDateTime, this.formatter)) {
            throw new CampusException("Error! A deadline task must have an end datetime in the correct format, please follow the following syntax: deadline <deadline name> /by <endDateTime (HHmm dd/MM/yyyy)>\n");
        } else {
            this.endDateTime = LocalDateTime.parse(endDateTime, this.formatter);
        }
    }

    public Deadline(String deadlineName, Boolean completed, String endDateTime) throws CampusException {
        this.taskName = deadlineName;
        this.completed = completed;
        if (!isValidDateTimeFormat(endDateTime, this.formatter)) {
            throw new CampusException("Error! A deadline task must have an end datetime in the correct format, please follow the following syntax: deadline <deadline name> /by <endDateTime (HHmm dd/MM/yyyy)>\n");
        } else {
            this.endDateTime = LocalDateTime.parse(endDateTime, this.formatter);
        }
    }

    @Override
    public void markComplete() {
        this.completed = true;
    }

    @Override
    public void markIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String xMarker = this.completed ? "[X]" : "[ ]";
        return String.format("[D] %s %s (by: %s)", xMarker, this.taskName, this.endDateTime.format(this.formatter));
    }

    @Override
    public String toDBFormat() {
        String completed = this.completed ? "1" : "0";
        return String.format("D | %s | %s | %s", completed, this.taskName, this.endDateTime.format(this.formatter));
    }
}
