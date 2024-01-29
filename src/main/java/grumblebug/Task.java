package grumblebug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    protected String description;
    protected boolean isDone;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected char taskType;

    // To-do
    public Task(boolean done, String description) {
        this.description = description;
        this.isDone = done;
        this.taskType = 'T';
    }

    // Deadline
    public Task(boolean done, String description, LocalDate end) {
        this.description = description;
        this.isDone = done;
        this.endDate = end;
        this.taskType = 'D';
    }

    // Event
    public Task(boolean done, String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.isDone = done;
        this.taskType = 'E';
        this.startDate = start;
        this.endDate = end;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFullStatus() {
        String status = ". [" + this.getStatusIcon() + "] ["
                + this.taskType + "] "
                + this.description;
        if (this.taskType == 'E') {
            status += "\nfrom: " + this.startDate.format(formatter);
        }
        if (this.taskType == 'D' || this.taskType == 'E') {
            status += "\nend: " + this.endDate.format(formatter);
        }
        return status;
    }

    public void setDone(boolean doneness) {
        this.isDone = doneness;
    }

}

