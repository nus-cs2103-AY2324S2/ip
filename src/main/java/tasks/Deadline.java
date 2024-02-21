package tasks;

import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String taskDescription, Boolean isDone, LocalDateTime by) {
        super(taskDescription, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy | HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D| " + super.toFileString() + "|" + this.by.format(formatter);
    }
}

// // D|1|description|yyyy-MM-dd_HH:mm