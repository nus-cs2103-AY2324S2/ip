package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String taskDescription, LocalDate start, LocalDate end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return String.format("T,%b,%s,%s,%s", isDone, taskDescription, start, end);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ",
                this.taskDescription,
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }
}
